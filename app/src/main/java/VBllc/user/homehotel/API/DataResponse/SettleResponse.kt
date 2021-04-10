package VBllc.user.homehotel.API.DataResponse

data class SettleResponse(
        val success: Boolean,
        val data: SettleData? = null,
        val errors: List<String>? = null
){
    data class SettleData(
            val settlement_start: String,
            val settlement_end: String,
            val uid: String,
            val hotel: HotelsPesponse.HotelData,
            val filial: FilialData,
            val apartament: ApartamentData,
            val buyer: UserInfoResponse.UserInfoData
    )
}