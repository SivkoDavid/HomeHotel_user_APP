package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.UserInfoResponse

interface ProfileView:BaseView {
    fun showLoginView(userInfo: UserInfoResponse.UserInfoData? = null)
    fun showNoLoginView()
}