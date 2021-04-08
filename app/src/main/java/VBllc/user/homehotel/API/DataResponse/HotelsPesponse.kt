package VBllc.user.homehotel.API.DataResponse

data class HotelsPesponse(
        val success: Boolean,
        val hotels: List<HotelData>? = null
){
    data class HotelData(
            val id: Int,
            val created_at: String,
            val updated_at: String,
            val name: String,
            val address: String,
            val main_phone: String,
            val additional_phone: String,
            val owner_id: Int
    )
}
