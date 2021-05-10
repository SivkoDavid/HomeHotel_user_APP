package VBllc.user.homehotel.API.DataResponse

import com.squareup.moshi.Json

data class FoodMenuResponse(
        val success: Boolean,
        @Json(name="products")val data: List<FoodData>? = null,
        val errors: Map<String, String>? = null
)