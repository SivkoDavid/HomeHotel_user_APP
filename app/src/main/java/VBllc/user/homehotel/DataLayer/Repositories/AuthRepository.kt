package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class AuthRepository(val listener: AuthRepositoryListener?) {

    fun login(login: String, pass: String, code: Int = 0){
        listener?.startRequest("requestForma", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                val response = postReq.login(login, pass)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener?.onLoginResponse(response.body()!!, code)
                        saveToken(response.body()!!.token?:"")
                    }
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            errors.add(it)
                        }
                        listener?.onErrors(errors, 200,  code)
                    }
                } else { //Ошибка сервера
                    listener?.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                listener?.noInternet()
            }
        }
    }

    fun registration(name:String, surname: String, email: String, pass: String, code: Int = 0){
        listener?.startRequest("requestForma", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                val response = postReq.registration(name, surname, email, pass)

                if (response.isSuccessful()) {
                    if(response.body()!!.success)
                        listener?.onRegistrationResponse(response.body()!!, code)
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            it.value.forEach{ value ->
                                errors.add(value)
                            }
                        }
                        listener?.onErrors(errors, 200,  code)
                    }
                } else { //Ошибка сервера
                    listener?.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                e.printStackTrace()
                listener?.noInternet()
            }
        }
    }



    private fun saveToken(token:String){
        UserInfoPreference.token.saveToken(token)
    }

    private fun getToken():String{
        return UserInfoPreference.token.toString()
    }

}