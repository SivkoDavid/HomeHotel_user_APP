package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.DataLayer.Preferences.SettlementPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class GuestRepository(val listener: GuestRepositoryListener) {

    fun checkConservedSettle(code: Int){
        val settleCode = SettlementPreference.getSettleCode()
        if(settleCode.isNotEmpty()){
            sendSettleCode(settleCode, code)
        }
    }


    fun sendSettleCode(setteleCode: String, code: Int){
        listener.startRequest("sendSettleCode", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                val response = postReq.getSettle(setteleCode)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        SettlementPreference.saveSettleCode(setteleCode)
                        listener.onSetteleResponse(response.body()!!, code)
                    }
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            errors.add(it)
                        }
                        listener.onErrors(errors, 200,  code)
                        getOutOfTheSettle()
                    }
                } else { //Ошибка сервера
                    listener.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                listener.noInternet()
            }
        }
    }

    fun getOutOfTheSettle(){
        SettlementPreference.removeSettleCode()
        listener.onExitSettle()
    }


}