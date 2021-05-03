package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Views.HotelServicesView

class HotelServicesPresenter(val view: HotelServicesView){

    private val repository = HotelServicesRepository(RepositoryListener())

    private val GET_SERVICES_CODE = 29

    var settle: SettleResponse.SettleData? = null
        set(value) {
            field = value
            if(field?.apartament?.id != null)
                repository.getHotelServices(field?.apartament?.id!!, GET_SERVICES_CODE)
        }


    inner class RepositoryListener: HotelServicesRepositoryListener{
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) {
            view.showServices(response.services?: listOf())
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