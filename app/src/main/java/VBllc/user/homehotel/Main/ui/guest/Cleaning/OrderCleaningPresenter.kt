package VBllc.user.homehotel.Main.ui.guest.Cleaning

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import VBllc.user.homehotel.API.DataResponse.SendOrderResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Views.OrderCleaningView
import android.widget.Toast

class OrderCleaningPresenter(val view: OrderCleaningView) {
    private val repository = HotelServicesRepository(RepositoryListener())

    private val SEND_ORDER_CODE = 29

    var settle: SettleResponse.SettleData? = null

    fun sendCleaningOrder(time: String?){
        repository.sendCleaningOrder(settle?.uid?:"", time, null, SEND_ORDER_CODE)
    }

    var lastServices: List<HotelServicesResponse.HotelServiceData>? = null

    inner class RepositoryListener: HotelServicesRepositoryListener {
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) {}

        override fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int) { }

        override fun onSendOrderResponse(response: SendOrderResponse, code: Int) {
            view.showToast("Заявка на услугу отправлена и вскоре будет обработана", Toast.LENGTH_LONG)
            view.backToMenu()
        }

        override fun startRequest(name: String, code: Int) {
        }

        override fun noInternet(code: Int?) {
            view.showNoNetwork()
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')
            view.showError(mess, errorCode)
        }
    }
}