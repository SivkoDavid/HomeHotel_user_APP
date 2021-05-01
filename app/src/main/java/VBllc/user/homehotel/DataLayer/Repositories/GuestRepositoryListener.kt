package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.SettleResponse

interface GuestRepositoryListener:BaseRepositoryListener {
    fun onSetteleResponse(settle: SettleResponse, code:Int)
    fun onExitSettle()
}