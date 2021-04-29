package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.HotelResponse
import VBllc.user.homehotel.API.DataResponse.HotelsResponse
import VBllc.user.homehotel.API.DataResponse.SendReviewResponse

interface HotelsRepositoryListener:BaseRepositoryListener {
    fun onHotelsResponse(hotels: List<HotelsResponse.HotelData>, code: Int)
    fun onHotelResponse(hotel: HotelResponse.HotelData?, code: Int)
    fun onSendReviewResponse(response: SendReviewResponse, code: Int){}
}