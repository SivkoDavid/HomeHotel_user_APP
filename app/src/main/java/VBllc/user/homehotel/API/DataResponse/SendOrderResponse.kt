package VBllc.user.homehotel.API.DataResponse

import com.squareup.moshi.Json

data class SendOrderResponse(
        val success: Boolean,
        val order: OrderData? = null,
        val errors: Map<String, List<String>>? = null
){
    data class OrderData(
            val id: Int
    )
}