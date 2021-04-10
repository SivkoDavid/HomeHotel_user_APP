package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.SettleResponse

interface GuestView: BaseView {
    fun showSettlement(data: SettleResponse.SettleData)

    fun showNoGuestMode()
}