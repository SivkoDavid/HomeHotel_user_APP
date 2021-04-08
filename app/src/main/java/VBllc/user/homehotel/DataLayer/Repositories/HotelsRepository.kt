package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HotelsRepository(val listener: HotelsRepositoryListener) {

    fun getHotels(code: Int = 0){
        listener?.startRequest("getUserInfo", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение
                val response = postReq.getHotels()

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener?.onHotelsResponse(response.body()!!.hotels?: listOf(), code)
                    }
                    else {
                        val errors = listOf("Ошибка")
                        listener?.onErrors(errors, 400,  code)
                    }
                } else { //Ошибка сервера
                    listener?.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                listener?.noInternet()
            }
        }
    }
}