package VBllc.user.homehotel.API.DataResponse

data class HotelServicesResponse(
    val success: Boolean,
    val services: List<HotelServiceData>? = null,
    val errors: List<String>? = null
){
    data class HotelServiceData(
            val id: Int,
            val name: String? = null,
            val price: Double? = null,
            val price_type: String? = null,
            val description: String? = null,
            val category: String? = null,
            val subcategory: String? = null,
            val picture: String? = null
    )

}
