package VBllc.user.homehotel.DataLayer.Preferences

import VBllc.user.homehotel.App.HomeHotelApp
import android.content.SharedPreferences

class HomeHotelPreference {

    val pref: SharedPreferences? = HomeHotelApp.appContext?.getSharedPreferences(PREFERENCE_NAME, android.content.Context.MODE_PRIVATE)

    companion object{
        private val PREFERENCE_NAME = "Sovetnik_preference"
        fun get():SharedPreferences?{
            return HomeHotelApp.appContext?.getSharedPreferences(PREFERENCE_NAME, android.content.Context.MODE_PRIVATE)!!
        }
    }
}