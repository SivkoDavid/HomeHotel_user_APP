package VBllc.user.homehotel.Main.ui.progile

import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import VBllc.user.homehotel.DataLayer.Repositories.ProfileRepository
import VBllc.user.homehotel.DataLayer.Repositories.ProfileRepositoryListener
import VBllc.user.homehotel.Views.ProfileView

class ProfilePresenter(val view: ProfileView) {

    private val repository: ProfileRepository = ProfileRepository(RepositoryObserver())

    init {
        if(repository.userIsAutorise())
            view.showLoginView()
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

        override fun startRequest(name: String, code: Int) {

        }

        override fun noInternet() {

        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {

        }

    }
}