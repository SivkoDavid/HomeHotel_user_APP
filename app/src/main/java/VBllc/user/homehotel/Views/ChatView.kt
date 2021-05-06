package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.ChatResponse

interface ChatView:BaseView {
    fun showChat(chat: ChatResponse.ChatData)

    fun updateChat(chat: ChatResponse.ChatData)

    fun addInCancelChat(mesages: List<ChatResponse.ChatData.MessageData>)

    fun addMessage(message: ChatResponse.ChatData.MessageData)

    fun removeMessage(message: ChatResponse.ChatData.MessageData)

    fun updateMessage(message: ChatResponse.ChatData.MessageData)
}