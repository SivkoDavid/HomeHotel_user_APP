package VBllc.user.homehotel.Main.ui.hotels.Full

import VBllc.user.homehotel.API.DataResponse.HotelResponse
import VBllc.user.homehotel.API.DataResponse.HotelsResponse
import VBllc.user.homehotel.API.DataResponse.SendReviewResponse
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import VBllc.user.homehotel.DataLayer.Repositories.HotelsRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelsRepositoryListener
import VBllc.user.homehotel.Views.FullHotelView

class FullHotelPresenter (val view: FullHotelView){
    init {
        view.showButtonSend(UserInfoPreference.userInfo.isNotEmpty())
    }

    private val SEND_REVIEW_CODE = 23
    private val GET_HOTELINFO_CODE = 23

    var hotel: HotelResponse.HotelData? = null
        set(value) {
            field = value
            view.showAllINfo(value)
            view.showButtonSend(UserInfoPreference.userInfo.isNotEmpty())
        }

    val repository = HotelsRepository(RepListener())

    fun reviewMade(rating: Int, review: String?){
        if(hotel?.hotel?.id != null){
            repository.sendReview(hotel?.hotel?.id!!, rating, review?:"", SEND_REVIEW_CODE)
        }
    }

    inner class RepListener: HotelsRepositoryListener{
        override fun onHotelsResponse(hotels: List<HotelsResponse.HotelData>, code: Int) {

        }

        override fun onHotelResponse(hotel: HotelResponse.HotelData?, code: Int) {
            view.showAllINfo(hotel?:this@FullHotelPresenter.hotel)
        }

        override fun onSendReviewResponse(response: SendReviewResponse, code: Int) {
            if(hotel?.hotel?.id != null){
                repository.getHotel(hotel?.hotel?.id!!, GET_HOTELINFO_CODE)
            }
        }

        override fun startRequest(name: String, code: Int) {
            when(code){
                SEND_REVIEW_CODE -> view.showLoadingSendReview()
            }
        }

        override fun noInternet(code: Int?) {
            when(code){
                SEND_REVIEW_CODE -> view.showErrorSendReview("Нет подключения к интернету")
            }
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var err = ""
            errorMessages.forEach{
                err += it + '\n'
            }
            err = err.substring(0,err.count()-1)

            when(code){
                SEND_REVIEW_CODE -> view.showErrorSendReview(err)
            }
        }

    }

}