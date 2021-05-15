package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse

interface HotelServicesView : BaseView{
    fun showServices(data: List<HotelServicesResponse.HotelServiceData>)
    fun showOrderLoading()
    fun showOrderError(message: String)
    fun showOrderOk(message: String)
    fun closeFull()
}