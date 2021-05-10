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

    fun getHotel(hotelId: Int, code: Int = 0){
        listener?.startRequest("getUserInfo", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение
                val response = postReq.getHotel(hotelId)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener?.onHotelResponse(response.body()!!.hotel, code)
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

    fun sendReview(hotelId: Int, rating: Int, review: String, code: Int){
        listener?.startRequest("sendReview", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение
                val response = postReq.sendHotelReview(
                    UserInfoPreference.token.getToken(),
                    hotelId,
                    review,
                    rating
                )

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener?.onSendReviewResponse(response.body()!!, code)
                    }
                    else {
                        val errors = listOf(response.body()!!.error?:"")
                        listener?.onErrors(errors, 400,  code)
                    }
                } else { //Ошибка сервера
                    listener?.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                listener?.noInternet(code)
            }
        }
    }



}