package VBllc.user.homehotel.Auth.Login

interface LoginFragmentListener {
    fun onLoginClick(login: String, password: String)

    fun onGoToReristrationClick()
}