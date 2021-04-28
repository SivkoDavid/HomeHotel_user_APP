package VBllc.user.homehotel.API.DataResponse

data class HotelResponse(
        val success: Boolean,
        val hotel: HotelData
){
    data class HotelData(
            val hotel: HotelsResponse.HotelData,
            val organisation: OrganizationData,
            val reviews: List<ReviewsData>
    )

    data class OrganizationData(
            val id: Int,
            val created_at: String,
            val updated_at: String,
            val name: String,
            val address: String,
            val main_phone: String,
            val additional_phone: String
    )

    data class ReviewsData(
            val id: Int,
            val created_at: String,
            val publish_time: String,
            val text: String,
            val rating: String,
            val user_name: String,
            val user_avatar: String,
            val user_email: String
    )
}