package VBllc.user.homehotel.API.DataResponse

data class ChatResponse(
    val success: Boolean,
    val chatData: ChatData? = null
){
    data class ChatData(
        val messages: MutableList<MessageData>? = null
    ){
        data class MessageData(
                val  id: Int,
                val text: String? = null,
                val userName: String? = null,
                val userId: Int? = null,
                val time: String? = null,
                var status: Statuses = Statuses.SENDED,
                var isMyMessge: Boolean = false
        ){
            enum class Statuses {
                SENDED, ERROR, SEND_PROCESS
            }
        }
    }
}