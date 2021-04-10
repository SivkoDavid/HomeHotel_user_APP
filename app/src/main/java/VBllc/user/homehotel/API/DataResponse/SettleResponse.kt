package VBllc.user.homehotel.API.DataResponse

data class SettleResponse(
        val success: Boolean,
        val data: SettleData
){
    data class SettleData(
            val uid: String
    )
}