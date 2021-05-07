package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.DataLayer.Repositories.ChatRepository
import VBllc.user.homehotel.DataLayer.Repositories.ChatRepositoryListener
import VBllc.user.homehotel.Views.ChatView

class ChatPresenter(val view: ChatView) {
    val repository = ChatRepository(RepositoryListener())

    private val REQUEST_ALL_CHAT_CODE = 23

    init {
        repository.getChat(REQUEST_ALL_CHAT_CODE)
    }

    inner class RepositoryListener: ChatRepositoryListener{
        override fun onChatResponse(response: ChatResponse, code: Int) {
            if(response.chatData != null)
                view.showChat(response.chatData)
        }

        override fun startRequest(name: String, code: Int) {

        }

        override fun noInternet(code: Int?) {

        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {

        }
    }
}