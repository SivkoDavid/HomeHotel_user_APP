package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.*

interface HotelServicesRepositoryListener:BaseRepositoryListener {
    fun onServicesResponse(response: HotelServicesResponse, code: Int)
    fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int)
    fun onFoodMenuResponse(response: FoodMenuResponse, code: Int){}
    fun onSendOrderResponse(response: SendOrderResponse, code: Int){}
}