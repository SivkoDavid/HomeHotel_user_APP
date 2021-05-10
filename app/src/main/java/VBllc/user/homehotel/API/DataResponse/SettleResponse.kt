package VBllc.user.homehotel.API.DataResponse

import com.squareup.moshi.Json

data class SettleResponse(
        val success: Boolean,
        @Json(name="settlement")val data: SettleData? = null,
        val errors: List<String>? = null
){
    data class SettleData(
            val settlement_start: String,
            val settlement_end: String,
            val uid: String,
            val hotel: HotelsResponse.HotelData,
            val filial: FilialData,
            val apartament: ApartamentData,
            val buyer: UserInfoResponse.UserInfoData
    )
}