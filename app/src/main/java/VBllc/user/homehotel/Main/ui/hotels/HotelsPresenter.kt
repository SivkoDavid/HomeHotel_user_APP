package VBllc.user.homehotel.Main.ui.hotels

import VBllc.user.homehotel.API.DataResponse.HotelsResponse
import VBllc.user.homehotel.DataLayer.Repositories.HotelsRepositoryListener
import VBllc.user.homehotel.DataLayer.Repositories.HotelsRepository
import VBllc.user.homehotel.Views.HotelsView

class HotelsPresenter(val view: HotelsView) {

    val repository = HotelsRepository(RepositoryListener())
    private val GET_HOTELS_CODE = 76

    init {
        repository.getHotels(GET_HOTELS_CODE)
    }

    fun hotelClick(hotel: HotelsResponse.HotelData){
        view.openHotel(hotel)
    }

    fun refreshHotels(){
        repository.getHotels(GET_HOTELS_CODE)
    }

    inner class RepositoryListener: HotelsRepositoryListener{
        override fun onHotelsResponse(hotels: List<HotelsResponse.HotelData>, code: Int) {
            view.showHotelsList(hotels)
        }

        override fun startRequest(name: String, code: Int) {
            view.showLoading()
        }

        override fun noInternet() {
            view.showNoNetwork()
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess +=it+"\n"
            }
            view.showError(mess, errorCode)
        }

    }
}