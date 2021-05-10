package VBllc.user.homehotel.API.DataResponse

import android.content.ClipDescription
import com.squareup.moshi.Json

data class FoodData(
    val id: Int,
    val name: String? = null,
    val description: String? = null,
    val imageLink: String? = null,
    val price: Float? = null,
    val origin_price: Float? = null,
    @Json(name="category_name")
    val category: String? = null
)