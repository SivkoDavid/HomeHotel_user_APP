package VBllc.user.homehotel.DataLayer.Preferences

import VBllc.user.homehotel.API.DataResponse.UserInfoResponse

class UserInfoPreference{
    companion object{
        val token = Token()
        val userInfo = Info()
    }
    class Token{
        private val TOKEN_FIELD_IN_PREF = "sivetnik_doma_token"
        private var MY_SOVETNIK_TOKEN = ""

        fun saveToken(token:String){
            //Сохраняем токен в преференсе
            val editor = HomeHotelPreference.get()?.edit()
            val _token = token

            editor?.putString(TOKEN_FIELD_IN_PREF, _token)
            editor?.apply()
            //Сохраняем токен в глобальной переменной
            MY_SOVETNIK_TOKEN = token
        }

        fun removeToken(){
            //Сохраняем пустой токен в преференсе
            val editor = HomeHotelPreference.get()?.edit()
            val _token = ""
            editor?.putString(TOKEN_FIELD_IN_PREF, _token)
            editor?.apply()
            //Сохраняем пустой токен в глобальной переменной
            MY_SOVETNIK_TOKEN = ""
        }

        fun getToken():String{
            val token = HomeHotelPreference.get()?.getString(TOKEN_FIELD_IN_PREF,"").toString()

            MY_SOVETNIK_TOKEN = token
            return token
        }

        override fun toString
                (): String {
            return getToken()
        }
    }

    class Info{
        private var data: UserInfoResponse.UserInfoData? = null
        private val FIELD_ID = "sovetnik_user_id"
        private val FIELD_NAME = "sovetnik_user_name"
        private val FIELD_SEC_NAME = "sovetnik_user_secname"
        private val FIELD_THIRD_NAME = "sovetnik_user_thirdname"
        private val FIELD_AVATAR = "sovetnik_user_avatar"
        private val FIELD_EMAIL = "sovetnik_user_email"
        private val FIELD_CREATE = "sovetnik_user_create"
        private val FIELD_UPDATE = "sovetnik_user_update"

        fun saveUserInfo(data: UserInfoResponse.UserInfoData?){
            if(data != null){
                HomeHotelPreference.get()?.edit()?.
                putInt(FIELD_ID, data.id)?.
                putString(FIELD_NAME, data.name)?.
                putString(FIELD_SEC_NAME, data.second_name)?.
                putString(FIELD_THIRD_NAME, data.third_name)?.
                putString(FIELD_AVATAR, data.avatar)?.
                putString(FIELD_EMAIL, data.email)?.
                putString(FIELD_CREATE, data.created_at)?.
                putString(FIELD_UPDATE, data.updated_at)?.
                apply()
                this.data = data
            }
        }

        fun clearUserInfo(){
            data = null
            HomeHotelPreference.get()?.edit()?.
            remove(FIELD_ID)?.
            remove(FIELD_NAME)?.
            remove(FIELD_SEC_NAME)?.
            remove(FIELD_THIRD_NAME)?.
            remove(FIELD_AVATAR)?.
            remove(FIELD_EMAIL)?.
            remove(FIELD_CREATE)?.
            remove(FIELD_UPDATE)?.
            apply()
        }

        fun isNotEmpty():Boolean{
            return HomeHotelPreference.get()?.getInt(FIELD_ID, -1) != -1
        }

        fun getInfo(): UserInfoResponse.UserInfoData? {
            if(data == null){
                val id = HomeHotelPreference.get()?.getInt(FIELD_ID, 0)?:0
                val name = HomeHotelPreference.get()?.getString(FIELD_NAME, "")?:""
                val email = HomeHotelPreference.get()?.getString(FIELD_EMAIL, "")?:""
                val secName = HomeHotelPreference.get()?.getString(FIELD_SEC_NAME, "")?:""
                val thName = HomeHotelPreference.get()?.getString(FIELD_THIRD_NAME, "")?:""
                val avatar = HomeHotelPreference.get()?.getString(FIELD_AVATAR, "")?:""
                val created_at = HomeHotelPreference.get()?.getString(FIELD_CREATE, "")?:""
                val updated_at = HomeHotelPreference.get()?.getString(FIELD_UPDATE, "")?:""


                data = UserInfoResponse.UserInfoData(
                        id,
                        name,
                        secName,
                        thName,
                        email,
                        avatar,
                        created_at,
                        updated_at)
            }
            return data
        }
    }
}