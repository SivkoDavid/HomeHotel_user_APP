package VBllc.user.homehotel.DataLayer.Preferences

class SettlementPreference {
    companion object {
        private val SETTLE_CODE_FIELD_IN_PREF = "sivetnik_doma_settlecode"
        private var MY_SOVETNIK_SETTLE_CODE = ""

        fun saveSettleCode(code: String) {
            //Сохраняем токен в преференсе
            val editor = HomeHotelPreference.get()?.edit()
            val _code = code

            editor?.putString(SETTLE_CODE_FIELD_IN_PREF, _code)
            editor?.apply()
            //Сохраняем токен в глобальной переменной
            MY_SOVETNIK_SETTLE_CODE = code
        }

        fun removeSettleCode() {
            //Сохраняем пустой токен в преференсе
            val editor = HomeHotelPreference.get()?.edit()
            val _token = ""
            editor?.putString(SETTLE_CODE_FIELD_IN_PREF, _token)
            editor?.apply()
            //Сохраняем пустой токен в глобальной переменной
            MY_SOVETNIK_SETTLE_CODE = ""
        }

        fun getSettleCode(): String {
            val token = HomeHotelPreference.get()?.getString(SETTLE_CODE_FIELD_IN_PREF, "").toString()

            MY_SOVETNIK_SETTLE_CODE = token
            return token
        }
    }
}