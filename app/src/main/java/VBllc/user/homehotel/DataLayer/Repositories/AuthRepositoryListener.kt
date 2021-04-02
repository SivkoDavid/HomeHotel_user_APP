package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.LoginResponse
import VBllc.user.homehotel.API.DataResponse.RegistrationResponse

interface AuthRepositoryListener {

    fun startRequest(name: String, code: Int = 0)

    fun onLoginResponse(data: LoginResponse, code: Int = 0)

    fun onRegistrationResponse(data: RegistrationResponse, code: Int = 0)

    fun onErrors(errorMessages: List<String> = listOf(), errorCode: Int, code: Int = 0)

    fun onLogout(result: Boolean)

    fun noInternet()
}