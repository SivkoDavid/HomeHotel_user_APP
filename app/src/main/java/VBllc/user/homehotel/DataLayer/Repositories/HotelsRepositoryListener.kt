package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.HotelsPesponse

interface HotelsRepositoryListener:BaseRepositoryListener {
    fun onHotelsResponse(hotels: List<HotelsPesponse.HotelData>, code: Int)
}