package VBllc.user.homehotel.Auth

import VBllc.user.homehotel.API.DataResponse.LoginResponse
import VBllc.user.homehotel.API.DataResponse.RegistrationResponse
import VBllc.user.homehotel.DataLayer.Repositories.AuthRepository
import VBllc.user.homehotel.DataLayer.Repositories.AuthRepositoryListener
import VBllc.user.homehotel.Views.AuthView
import kotlinx.coroutines.CoroutineScope

class AuthPresenter(val view: AuthView){
    private val authRepository = AuthRepository(AuthRepositoryObserver())

    private val LOGIN_REQEST_CODE = 67
    private val REGISTRATION_REQEST_CODE = 76

    fun login(login: String, pass: String){
        authRepository.login(login, pass, LOGIN_REQEST_CODE)
    }

    fun registration(name:String, surname: String, email: String, pass: String){
        authRepository.registration(name, surname, email, pass, REGISTRATION_REQEST_CODE)
    }

    private inner class AuthRepositoryObserver:AuthRepositoryListener{
        override fun startRequest(name: String, code: Int) {
            view.showLoading()
        }

        override fun onLoginResponse(data: LoginResponse, code: Int) {
            view.showAutorise()
        }

        override fun onRegistrationResponse(data: RegistrationResponse, code: Int) {
            view.showAutorise()
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')
            var typeError = 0
            when(code){
                LOGIN_REQEST_CODE->{
                    typeError = AuthView.LOGIN_ERROR
                }
                REGISTRATION_REQEST_CODE->{
                    typeError = AuthView.REGISTRATION_ERROR
                }
            }
            view.showErrorAutorise(mess, errorCode, typeError)
        }

        override fun onLogout(result: Boolean) {

        }

        override fun noInternet(code: Int?) {
            view.showNoNetwork()
        }

    }
}