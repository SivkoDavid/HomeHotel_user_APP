package VBllc.user.homehotel.API.DataResponse

import com.squareup.moshi.Json

data class ChatResponse(
        val success: Boolean,
        @Json(name="chat")val chatData: ChatData? = null,
        val errors: Map<String, List<String>>? = null
){
    data class ChatData(
        val id: Int,
        val messages: MutableList<MessageData>? = null
    ){
        data class MessageData(
                var id: Int,
                var text: String? = null,
                val userName: String? = null,
                @Json(name="user_id") var userId: Int? = null,
                @Json(name="created_at") val time: String? = null,
                var status: Statuses = Statuses.SENDED,
                var isMyMessge: Boolean = false
        ){
            enum class Statuses {
                SENDED, ERROR, SEND_PROCESS
            }
        }
    }
}
