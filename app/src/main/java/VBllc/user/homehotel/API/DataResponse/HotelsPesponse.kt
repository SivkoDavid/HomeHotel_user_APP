package VBllc.user.homehotel.API.DataResponse

data class HotelsPesponse(
        val success: Boolean,
        val hotels: List<HotelData>? = null
){
    data class HotelData(
            val id: Int,
            val created_at: String?=null,
            val updated_at: String?=null,
            val name: String?=null,
            val address: String?=null,
            val main_phone: String?=null,
            val additional_phone: String?=null,
            val owner_id: Int?=null
    )
}
