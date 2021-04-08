package VBllc.user.homehotel.Main.ui.profile

import VBllc.user.homehotel.API.DataResponse.UserInfoResponse
import VBllc.user.homehotel.DataLayer.Repositories.ProfileRepository
import VBllc.user.homehotel.DataLayer.Repositories.ProfileRepositoryListener
import VBllc.user.homehotel.Views.ProfileView

class ProfilePresenter(val view: ProfileView) {

    private val USER_INFO_CODE = 281
    private val repository: ProfileRepository = ProfileRepository(RepositoryObserver())

    init {
        if(repository.userIsAutorise()) {
            view.showLoginView()
            repository.getUserInfo(code = USER_INFO_CODE)
        }
        else
            view.showNoLoginView()
    }

    fun logout(){
        repository.logout()
    }

    inner class RepositoryObserver: ProfileRepositoryListener{
        override fun onLogout(status: Boolean) {
            view.showNoLoginView()
        }

        override fun onUserInfoResponse(userInfo: UserInfoResponse.UserInfoData?, code: Int) {
            if(userInfo != null){
                view.showLoginView(userInfo)
            }
        }

        override fun startRequest(name: String, code: Int) {

        }

        override fun noInternet() {

        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {

        }

    }
}