package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse

interface HotelServicesView : BaseView{
    fun showServices(data: List<HotelServicesResponse.HotelServiceData>)
    fun closeFull()
}