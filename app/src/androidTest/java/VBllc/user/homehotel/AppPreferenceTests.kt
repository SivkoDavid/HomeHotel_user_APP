package VBllc.user.homehotel

import VBllc.user.homehotel.API.DataResponse.UserInfoResponse
import VBllc.user.homehotel.DataLayer.Preferences.HomeHotelPreference
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import android.content.Context
import android.preference.Preference
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppPreferenceTests {

    var appContext: Context? = null
    val PREF_NAME = "Sovetnik_preference"
    val TOKEN_FIELD = "sivetnik_doma_token"

    @Before
    fun initialContext(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    //-------------------  Tests User Token ---------------------

    @Test
    fun saveToken(){
        UserInfoPreference.token.saveToken("token12345")
        val pref = appContext?.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)

        val actual = pref?.getString(TOKEN_FIELD, "")

        Assert.assertEquals("Токен сохранился неверно", "token12345", actual)
    }

    @Test
    fun getToken(){
        val pref = appContext?.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)
        pref?.edit()?.putString(TOKEN_FIELD, "0078987")?.commit()
        val actual = UserInfoPreference.token.toString()
        Assert.assertEquals("Получен неверный токен","0078987",actual)
    }

    @Test
    fun clearToken(){
        UserInfoPreference.token.removeToken()
        val pref = appContext?.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)

        val actual = pref?.getString(TOKEN_FIELD, "")

        Assert.assertEquals("Токен не удалился", true, actual?.isEmpty())
    }

    //-------------------  Tests User Info ---------------------

    val testUserInfo = UserInfoResponse.UserInfoData(
        897,
        "Степан",
        "Степанов",
        "Степанович",
        "plovStep@mail.com",
        "",
        "",
        ""
    )

    val FIELD_ID = "sovetnik_user_id"
    val FIELD_NAME = "sovetnik_user_name"
    val FIELD_SEC_NAME = "sovetnik_user_secname"
    val FIELD_THIRD_NAME = "sovetnik_user_thirdname"
    val FIELD_AVATAR = "sovetnik_user_avatar"
    val FIELD_EMAIL = "sovetnik_user_email"
    val FIELD_CREATE = "sovetnik_user_create"
    val FIELD_UPDATE = "sovetnik_user_update"

    @Test
    fun saveNewUserInfo(){

        UserInfoPreference.userInfo.saveUserInfo(testUserInfo)
        val pref = appContext?.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)

        val actual = UserInfoResponse.UserInfoData(
            pref?.getInt(FIELD_ID, -1)?:-1,
            pref?.getString(FIELD_NAME, ""),
            pref?.getString(FIELD_SEC_NAME, ""),
            pref?.getString(FIELD_THIRD_NAME, ""),
            pref?.getString(FIELD_EMAIL, ""),
            pref?.getString(FIELD_AVATAR, ""),
            pref?.getString(FIELD_CREATE, ""),
            pref?.getString(FIELD_UPDATE, "")
        )

        Assert.assertEquals("Информация пользователя сохранилась неверно", testUserInfo, actual)
    }

    @Test
    fun getUserInfo(){
        val pref = appContext?.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)
        pref?.edit()
            ?.putInt(FIELD_ID, testUserInfo.id)
            ?.putString(FIELD_NAME, testUserInfo.name)
            ?.putString(FIELD_SEC_NAME, testUserInfo.second_name)
            ?.putString(FIELD_THIRD_NAME, testUserInfo.third_name)
            ?.putString(FIELD_EMAIL, testUserInfo.email)
            ?.putString(FIELD_AVATAR, testUserInfo.avatar)
            ?.putString(FIELD_CREATE, testUserInfo.created_at)
            ?.putString(FIELD_UPDATE, testUserInfo.updated_at)
            ?.commit()
        val actual = UserInfoPreference.userInfo.getInfo()

        Assert.assertEquals("Получен неверные данные пользователя",testUserInfo,actual)
    }

    @Test
    fun clearUserInfo(){
        UserInfoPreference.userInfo.clearUserInfo()
        val pref = appContext?.getSharedPreferences(PREF_NAME, android.content.Context.MODE_PRIVATE)

        val actual = UserInfoResponse.UserInfoData(
            pref?.getInt(FIELD_ID, -1)?:-1,
            pref?.getString(FIELD_NAME, ""),
            pref?.getString(FIELD_SEC_NAME, ""),
            pref?.getString(FIELD_THIRD_NAME, ""),
            pref?.getString(FIELD_EMAIL, ""),
            pref?.getString(FIELD_AVATAR, ""),
            pref?.getString(FIELD_CREATE, ""),
            pref?.getString(FIELD_UPDATE, "")
        )

        val res = actual.id == -1 &&
                actual.name == "" &&
                actual.second_name == "" &&
                actual.third_name == "" &&
                actual.email == "" &&
                actual.avatar == "" &&
                actual.created_at == "" &&
                actual.updated_at == ""

        Assert.assertEquals("Данные пользователя не удалились", true, res)
    }

}