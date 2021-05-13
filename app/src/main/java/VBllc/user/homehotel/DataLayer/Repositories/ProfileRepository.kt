package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.DataLayer.Preferences.SettlementPreference
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ProfileRepository(val listener: ProfileRepositoryListener) {
    fun userIsAutorise():Boolean{
        return UserInfoPreference.token.getToken().isNotEmpty()
    }

    fun logout(){
        UserInfoPreference.token.removeToken()
        UserInfoPreference.userInfo.clearUserInfo()
        SettlementPreference.removeSettleCode()
        listener?.onLogout(true)
    }
    
    fun getUserInfo(token: String = UserInfoPreference.token.toString(), code:Int = 0){
        listener?.startRequest("getUserInfo", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение
                listener.onUserInfoResponse(UserInfoPreference.userInfo.getInfo(), code)

                val response = postReq.getUserInfo(token)

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        listener?.onUserInfoResponse(response.body()!!.user, code)
                        UserInfoPreference.userInfo.saveUserInfo(response.body()!!.user)
                    }
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            it.value.forEach{
                                errors.add(it)
                            }
                        }
                        listener?.onErrors(errors, response.body()!!.errors?.keys?.first()?.toInt()?:400,  code)
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