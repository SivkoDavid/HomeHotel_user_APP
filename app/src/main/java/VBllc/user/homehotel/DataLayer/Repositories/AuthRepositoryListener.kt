package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.LoginResponse
import VBllc.user.homehotel.API.DataResponse.RegistrationResponse

interface AuthRepositoryListener:BaseRepositoryListener {

    fun onLoginResponse(data: LoginResponse, code: Int = 0)

    fun onRegistrationResponse(data: RegistrationResponse, code: Int = 0)

    fun onLogout(result: Boolean)
}