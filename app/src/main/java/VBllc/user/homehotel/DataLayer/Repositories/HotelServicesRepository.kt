package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class HotelServicesRepository(val listener: HotelServicesRepositoryListener) {

    fun getHotelServices(roomId: Int, code: Int){
        listener.startRequest("getHotelServices", code)
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

    fun getPartnerServices(filialId: Int, code: Int){
        listener.startRequest("getPartnerServices", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                val response = postReq.getPrtnerServices(filialId)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener.onPartnerServicesResponse(response.body()!!, code)
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

    fun getFoodMenu(settleCode: String, code: Int){
        listener.startRequest("getFoodMenu", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                val response = postReq.getProducts(settleCode)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener.onFoodMenuResponse(response.body()!!, code)
                    }
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            errors.add(it.value)
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