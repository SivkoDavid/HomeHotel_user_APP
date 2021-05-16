package VBllc.user.homehotel.Main.ui.guest.Hygiene

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import VBllc.user.homehotel.API.DataResponse.SendOrderResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Views.OrderCleaningView
import android.widget.Toast

class OrderHyegenePresenter(val view: OrderCleaningView) {
    private val repository = HotelServicesRepository(RepositoryListener())

    private val SEND_ORDER_CODE = 29

    var settle: SettleResponse.SettleData? = null

    fun sendCleaningOrder(time: String?){
        repository.sendHugieneOrder(settle?.uid?:"", time, null, SEND_ORDER_CODE)
    }

    var lastServices: List<HotelServicesResponse.HotelServiceData>? = null

    inner class RepositoryListener: HotelServicesRepositoryListener {
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) {}

        override fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int) { }

        override fun onSendOrderResponse(response: SendOrderResponse, code: Int) {
            view.showToast("Заявка на услугу отправлена и вскоре будет обработана", Toast.LENGTH_LONG)
            view.showOkDialog("Заявка на смену гигиенических средств отправлена и вскоре будет обработана")
            view.backToMenu()
        }

        override fun startRequest(name: String, code: Int) {
            when(code){
                SEND_ORDER_CODE -> view.showLoadingDialog("")
            }
        }

        override fun noInternet(code: Int?) {
            view.showErrorDialog("Нет подключения к интернету")
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')
            when(code){
                SEND_ORDER_CODE -> view.showErrorDialog(mess)
                else -> view.showError(mess, errorCode)
            }
        }
    }
}