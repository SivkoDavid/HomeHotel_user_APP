package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.UserInfoResponse

interface ProfileRepositoryListener:BaseRepositoryListener {
    fun onLogout(status:Boolean)

    fun onUserInfoResponse(userInfo: UserInfoResponse.UserInfoData? , code:Int)
}