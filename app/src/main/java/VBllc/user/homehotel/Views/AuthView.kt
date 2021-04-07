package VBllc.user.homehotel.Views

import VBllc.user.homehotel.Views.BaseView

interface AuthView: BaseView {
    fun showAutorise()
    companion object{
        val REGISTRATION_ERROR = 1
        val LOGIN_ERROR = 2
    }

    fun showErrorAutorise(errorMessage: String, errorCode: Int, typeError: Int = LOGIN_ERROR)

}