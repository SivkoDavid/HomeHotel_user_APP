package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.HotelResponse

interface FullHotelView:BaseView {
    fun showLoadingSendReview()
    fun showErrorSendReview(errorMessage: String)
    fun showAllINfo(hotel: HotelResponse.HotelData?)
    fun showButtonSend(isShow: Boolean)
}