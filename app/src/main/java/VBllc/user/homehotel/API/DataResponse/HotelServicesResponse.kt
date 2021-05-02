package VBllc.user.homehotel.API.DataResponse

data class HotelServicesResponse(
    val success: Boolean,
    val services: List<HotelServiceData>? = null,
    val errors: List<String>? = null
){
    data class HotelServiceData(
        val id: Int,
        val name: String = "",
        val price: Double = 0.0,
        val priceType: String = "",
        val description: String = "",
        val category: String = "",
        val imgUrl: String = ""
    )
}
