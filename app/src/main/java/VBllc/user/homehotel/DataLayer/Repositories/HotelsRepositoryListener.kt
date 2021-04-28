package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.HotelsResponse

interface HotelsRepositoryListener:BaseRepositoryListener {
    fun onHotelsResponse(hotels: List<HotelsResponse.HotelData>, code: Int)
}