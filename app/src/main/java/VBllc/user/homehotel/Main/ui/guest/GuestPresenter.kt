package VBllc.user.homehotel.Main.ui.guest

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.GuestRepository
import VBllc.user.homehotel.DataLayer.Repositories.GuestRepositoryListener
import VBllc.user.homehotel.Views.GuestView

class GuestPresenter(private val view: GuestView) {

    private val SEND_SETTLECODE_CODE = 1
    private val OUT_SETTLE_CODE = 2
    private val CHECK_CONSERVED_SETTLE_CODE = 3

    private var data: SettleResponse.SettleData? = null


    private val repository: GuestRepository = GuestRepository(RepositoryListener())

    init {
        //repository.checkConservedSettle(CHECK_CONSERVED_SETTLE_CODE)
    }

    fun checkConservedSettle(){
        repository.checkConservedSettle(CHECK_CONSERVED_SETTLE_CODE)
    }

    fun sendSetteleCode( settleCode: String){
        repository.sendSettleCode(settleCode, SEND_SETTLECODE_CODE)
    }

    fun goToCleaningMenu(){
        if(data != null)
            view.showCleaningFragment(data!!)
    }

    fun goToHotelServicesMenu(){
        if(data != null)
            view.showHotelServicesFragment(data!!)
    }

    fun goToPartnersServicesMenu(){
        if(data != null)
            view.showPartnersServicesFragment(data!!)
    }

    fun goToHygieneMenu(){
        if(data != null)
            view.showHygieneFragment(data!!)
    }

    fun goToGastronomyMenu(){
        if(data != null)
            view.showGastronomyFragment(data!!)
    }

    fun goToChatMenu(){
        if(data != null)
            view.showChatFragment(data!!)
    }



    fun outOfTheSettle(){
        repository.getOutOfTheSettle(OUT_SETTLE_CODE)
    }


    private inner class RepositoryListener: GuestRepositoryListener{
        override fun onSetteleResponse(settle: SettleResponse, code: Int) {
            if(settle.data != null) {
                view.showSettlement(settle.data, code == SEND_SETTLECODE_CODE)
                data = settle.data
            }
            else
                onErrors(listOf("Ошибка"), 500, SEND_SETTLECODE_CODE)
        }

        override fun onExitSettle(code: Int) {
            view.showNoGuestMode()
            when(code){
                OUT_SETTLE_CODE -> view.infoDialodHide()
            }
        }

        override fun startRequest(name: String, code: Int) { view.showLoading() }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')
            view.showError(mess, errorCode)
        }

        override fun noInternet(code: Int?) {
            view.showNoNetwork()
        }

    }
}