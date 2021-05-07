package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.DataLayer.Repositories.ChatRepository
import VBllc.user.homehotel.DataLayer.Repositories.ChatRepositoryListener
import VBllc.user.homehotel.Views.ChatView

class ChatPresenter(val view: ChatView) {
    val repository = ChatRepository(RepositoryListener())

    private val REQUEST_ALL_CHAT_CODE = 23
    private val SEND_MESSAGE_CODE = 43

    init {
        repository.getChat(REQUEST_ALL_CHAT_CODE)
    }

    fun sendMessege(message: String){
        repository.sendMessage(message, SEND_MESSAGE_CODE)
    }

    inner class RepositoryListener: ChatRepositoryListener{
        override fun onChatResponse(response: ChatResponse, code: Int) {
            if(response.chatData != null)
                view.showChat(response.chatData)
        }

        override fun messageDelivered(messageData: ChatResponse.ChatData.MessageData, code: Int) {
            view.updateMessage(messageData)
        }

        override fun messageStartedSending(messageData: ChatResponse.ChatData.MessageData, code: Int) {
            view.addMessage(messageData)
        }

        override fun messageNotDelivered(messageData: ChatResponse.ChatData.MessageData, code: Int) {
            view.updateMessage(messageData)
        }

        override fun startRequest(name: String, code: Int) {

        }

        override fun noInternet(code: Int?) {

        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {

        }
    }
}