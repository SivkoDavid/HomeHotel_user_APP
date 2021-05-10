package VBllc.user.homehotel.API.DataResponse

data class SendReviewResponse(
    val success: Boolean,
    val error: String? = null,
    val review: HotelResponse.ReviewsData? = null
)