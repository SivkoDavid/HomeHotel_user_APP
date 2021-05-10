package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.ChatResponse

interface ChatRepositoryListener:BaseRepositoryListener {
    fun onChatResponse(response: ChatResponse, code: Int)

    fun messageDelivered(messageData: ChatResponse.ChatData.MessageData, code: Int)
    fun messageStartedSending(messageData: ChatResponse.ChatData.MessageData, code: Int)
    fun messageNotDelivered(messageData: ChatResponse.ChatData.MessageData, code: Int)
}