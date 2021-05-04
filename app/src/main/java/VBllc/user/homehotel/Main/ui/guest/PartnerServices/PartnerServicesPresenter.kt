package VBllc.user.homehotel.Main.ui.guest.PartnerServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Views.PartnerServicesView

class PartnerServicesPresenter(val view: PartnerServicesView) {
    private val repository = HotelServicesRepository(RepositoryListener())

    private val GET_PARTNER_SERVICES_CODE = 12

    var settle: SettleResponse.SettleData? = null
        set(value) {
            field = value
            if(field?.apartament?.id != null)
                repository.getPartnerServices(field?.filial?.id!!, GET_PARTNER_SERVICES_CODE)
        }

    fun refresh(settle: SettleResponse.SettleData?){
        this.settle = settle
    }


    inner class RepositoryListener: HotelServicesRepositoryListener {
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) {}
        override fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int) {
            view.showServices(response.partner_services?: listOf())
        }

        override fun startRequest(name: String, code: Int) {
            view.showLoading()
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