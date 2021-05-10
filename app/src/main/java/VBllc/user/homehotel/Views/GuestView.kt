package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.SettleResponse

interface GuestView: BaseView {
    fun showSettlement(data: SettleResponse.SettleData, showDialog: Boolean = false)

    fun showNoGuestMode()

    fun infoDialodHide()

    fun showCleaningFragment(data: SettleResponse.SettleData)
    fun showHotelServicesFragment(data: SettleResponse.SettleData)
    fun showPartnersServicesFragment(data: SettleResponse.SettleData)
    fun showHygieneFragment(data: SettleResponse.SettleData)
    fun showGastronomyFragment(data: SettleResponse.SettleData)
    fun showChatFragment(data: SettleResponse.SettleData)
}