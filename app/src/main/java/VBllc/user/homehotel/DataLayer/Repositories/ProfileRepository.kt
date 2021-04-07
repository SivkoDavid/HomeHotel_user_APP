package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference

class ProfileRepository(val listener: ProfileRepositoryListener) {
    fun userIsAutorise():Boolean{
        return UserInfoPreference.token.getToken().isNotEmpty()
    }

    fun logout(){
        UserInfoPreference.token.removeToken()
        UserInfoPreference.userInfo.clearUserInfo()
        listener?.onLogout(true)
    }
}