package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse

interface PartnerServicesView:BaseView {
    fun showServices(data: List<PartnersServicesResponse.PartnerServiceData>)
}