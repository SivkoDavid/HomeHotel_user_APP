package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.DataLayer.Preferences.SettlementPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HotelServicesRepository(val listener: HotelServicesRepositoryListener) {

    fun getHotelServices(roomId: Int, code: Int){
        listener.startRequest("sendSettleCode", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                val response = postReq.getHotelServices(roomId)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener.onServicesResponse(response.body()!!, code)
                    }
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            errors.add(it)
                        }
                        listener.onErrors(errors, 200,  code)
                    }
                } else { //Ошибка сервера
                    listener.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                listener.noInternet()
            }
        }
    }
}