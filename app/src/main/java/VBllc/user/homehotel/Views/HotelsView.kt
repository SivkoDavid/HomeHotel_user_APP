package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.HotelResponse
import VBllc.user.homehotel.API.DataResponse.HotelsResponse

interface HotelsView : BaseView{
    fun showHotelsList(hotels: List<HotelsResponse.HotelData>)

    fun openHotel(hotel: HotelResponse.HotelData)
}