package VBllc.user.homehotel.API.DataResponse

data class PartnersServicesResponse(
    val success: Boolean,
    val services: List<PartnerServiceData>? = null,
    val errors: List<String>? = null
){
    data class PartnerServiceData(
        val id: Int,
        val name: String? = null,
        val price_type: String? = null,
        val description: String? = null,
        val category: String? = null,
        val subcategory: String? = null,
        val picture: String? = null
    )

}