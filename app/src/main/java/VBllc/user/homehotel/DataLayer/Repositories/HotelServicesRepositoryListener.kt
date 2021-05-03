package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse

interface HotelServicesRepositoryListener:BaseRepositoryListener {
    fun onServicesResponse(response: HotelServicesResponse, code: Int)
}