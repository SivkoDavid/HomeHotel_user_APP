package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.API.DataResponse.FoodData
import VBllc.user.homehotel.API.DataResponse.FoodMenuResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

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

    fun getPartnerServices(filialId: Int, code: Int){
        listener.startRequest("sendSettleCode", code)
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

    fun getFoodMenu(filialId: Int, code: Int){
        listener.startRequest("sendSettleCode", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val test = listOf<FoodData>(
                FoodData(1, "Блюдо 1", "", "", 300f, "Категория 1"),
                FoodData(1, "Блюдо 2", "", "", 300f, "Категория 2"),
                FoodData(1, "Блюдо 3", "", "", 300f, "Категория 3"),
                FoodData(1, "Блюдо 4", "", "", 300f, "Категория 2"),
                FoodData(1, "Блюдо 5", "", "", 300f, "Категория 3"),
                FoodData(1, "Блюдо 6", "", "", 300f, "Категория 3"),
                FoodData(1, "Блюдо 7", "", "", 300f, "Категория 4"),
                FoodData(1, "Блюдо 8", "", "", 300f, "Категория 1"),
                FoodData(1, "Блюдо 9", "", "", 300f, "Категория 2"),
                FoodData(1, "Блюдо 10", "", "", 300f, "Категория 4"),
                FoodData(1, "Блюдо 11", "", "", 300f, "Категория 3"),
                FoodData(1, "Блюдо 12", "", "", 300f, "Категория 1"),
                FoodData(1, "Блюдо 13", "", "", 300f, "Категория 1"),
                FoodData(1, "Блюдо 14", "", "", 300f, "Категория 2"),
                FoodData(1, "Блюдо 15", "", "", 300f, "Категория 4"),
                FoodData(1, "Блюдо 16", "", "", 300f, "Категория 5"),
                FoodData(1, "Блюдо 17", "", "", 300f, "Категория 1"),
                FoodData(1, "Блюдо 18", "", "", 300f, "Категория 2"),
                FoodData(1, "Блюдо 19", "", "", 300f, "Категория 2"),
                FoodData(1, "Блюдо 20", "", "", 300f, "Категория 5"),
                FoodData(1, "Блюдо 21", "", "", 300f, "Категория 2")
            )
            delay(2000)
            listener.onFoodMenuResponse(FoodMenuResponse(true, data = test), code)
        }
    }
}