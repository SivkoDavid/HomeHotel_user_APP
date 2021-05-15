package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import VBllc.user.homehotel.API.DataResponse.SendOrderResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Views.HotelServicesView
import android.widget.Toast

class HotelServicesPresenter(val view: HotelServicesView){

    private val repository = HotelServicesRepository(RepositoryListener())

    private val GET_SERVICES_CODE = 29
    private val SEND_ORDER_CODE = 43

    var settle: SettleResponse.SettleData? = null
        set(value) {
            field = value
            if(field?.apartament?.id != null)
                repository.getHotelServices(field?.apartament?.id!!, GET_SERVICES_CODE)
        }

    fun refresh(settle: SettleResponse.SettleData?){
        this.settle = settle
    }

    fun sendOrder(serviceId: Int, time: String?, productIdList: List<Int>?){
        repository.sendOrder(settle?.uid?:"", serviceId, time, productIdList, SEND_ORDER_CODE)
    }

    var lastServices: List<HotelServicesResponse.HotelServiceData>? = null

    inner class RepositoryListener: HotelServicesRepositoryListener{
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) {
            lastServices = response?.services
            view.showServices(response.services?: listOf())
        }

        override fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int) { }

        override fun onSendOrderResponse(response: SendOrderResponse, code: Int) {
            view.closeFull()
            view.showToast("Заявка на услугу отправлена и вскоре будет обработана", Toast.LENGTH_LONG)
            view.showOrderOk("Заявка  на услугу отправлена и вскоре будет обработана")
        }

        override fun startRequest(name: String, code: Int) {
            when(code){
                SEND_ORDER_CODE -> view.showOrderLoading()
                else -> view.showLoading()
            }
        }

        override fun noInternet(code: Int?) {
            when(code){
                SEND_ORDER_CODE -> view.showOrderError("Отсутствует подключение к интернету")
                else -> view.showNoNetwork()
            }
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')
            when(code){
                SEND_ORDER_CODE -> view.showOrderError(mess)
                else -> view.showError(mess, errorCode)
            }

        }
    }
}