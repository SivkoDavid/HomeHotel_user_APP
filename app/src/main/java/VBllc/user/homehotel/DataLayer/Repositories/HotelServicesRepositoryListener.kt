package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse

interface HotelServicesRepositoryListener:BaseRepositoryListener {
    fun onServicesResponse(response: HotelServicesResponse, code: Int)
    fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int)
}