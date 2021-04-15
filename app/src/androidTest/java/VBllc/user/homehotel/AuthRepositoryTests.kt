package VBllc.user.homehotel

import VBllc.user.homehotel.API.DataResponse.LoginResponse
import VBllc.user.homehotel.API.DataResponse.RegistrationResponse
import VBllc.user.homehotel.API.DataResponse.UserInfoResponse
import VBllc.user.homehotel.DataLayer.Preferences.HomeHotelPreference
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import VBllc.user.homehotel.DataLayer.Repositories.AuthRepository
import VBllc.user.homehotel.DataLayer.Repositories.AuthRepositoryListener
import android.content.Context
import android.preference.Preference
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthRepositoryTests {

    var appContext: Context? = null

    @Before
    fun initialContext(){
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun loginWithCorrectData(){
        val repository = AuthRepository(object : AuthRepositoryListener{
            override fun onLoginResponse(data: LoginResponse, code: Int) { Assert.assertTrue(false) }

            override fun onRegistrationResponse(data: RegistrationResponse, code: Int) {  }

            override fun onLogout(result: Boolean) { Assert.assertTrue(false) }

            override fun startRequest(name: String, code: Int) { }

            override fun noInternet() {  }

            override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) { Assert.assertTrue(false) }

        })

        repository.login("admin1@gmail.com", "123123")

    }
}