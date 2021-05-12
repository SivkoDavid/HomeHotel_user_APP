package VBllc.user.homehotel.API.DataResponse

data class MessageResponse(
        val success: Boolean,
        val message: ChatResponse.ChatData.MessageData? = null,
        val errors: Map<String, List<String>>? = null
)
