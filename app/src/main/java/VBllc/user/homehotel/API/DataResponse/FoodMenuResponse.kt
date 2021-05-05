package VBllc.user.homehotel.API.DataResponse

data class FoodMenuResponse(
    val success: Boolean,
    val data: List<FoodData>? = null,
    val errors: List<String>? = null
)