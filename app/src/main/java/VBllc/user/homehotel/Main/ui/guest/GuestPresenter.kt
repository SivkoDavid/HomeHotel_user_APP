package VBllc.user.homehotel.Main.ui.guest

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.GuestRepository
import VBllc.user.homehotel.DataLayer.Repositories.GuestRepositoryListener
import VBllc.user.homehotel.Views.GuestView

class GuestPresenter(private val view: GuestView) {

    private val SEND_SETTELECODE_CODE = 1

    private val repository: GuestRepository = GuestRepository(RepositoryListener())

    init {
        repository.checkConservedSettle(SEND_SETTELECODE_CODE)
    }

    fun sendSetteleCode( settleCode: String){
        repository.sendSettleCode(settleCode, SEND_SETTELECODE_CODE)
    }

    fun goToCleaningMenu(){
        view.showCleaningFragment()
    }

    fun outOfTheSettle(){
        repository.getOutOfTheSettle()
    }


    private inner class RepositoryListener: GuestRepositoryListener{
        override fun onSetteleResponse(settle: SettleResponse, code: Int) {
            if(settle.data != null)
                view.showSettlement(settle.data)
        }

        override fun onExitSettle() {
            view.showNoGuestMode()
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