package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.HotelsPesponse

interface HotelsView : BaseView{
    fun showHotelsList(hotels: List<HotelsPesponse.HotelData>)

    fun openHotel(hotel: HotelsPesponse.HotelData)
}