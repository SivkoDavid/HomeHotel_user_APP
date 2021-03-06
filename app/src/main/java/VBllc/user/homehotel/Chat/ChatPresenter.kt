package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.DataLayer.Repositories.ChatRepository
import VBllc.user.homehotel.DataLayer.Repositories.ChatRepositoryListener
import VBllc.user.homehotel.Views.ChatView
import kotlinx.coroutines.*

class ChatPresenter(val view: ChatView) {
    val repository = ChatRepository(RepositoryListener())

    private val REQUEST_ALL_CHAT_CODE = 23
    private val LOOP_REQUEST_ALL_CHAT_CODE = 12
    private val SEND_MESSAGE_CODE = 43

    private var chatData: ChatResponse.ChatData? = null

    init {
        repository.getChat(REQUEST_ALL_CHAT_CODE)

        startLooper()
    }

    fun refresh(){
        repository.getChat(REQUEST_ALL_CHAT_CODE)
    }

    fun sendMessege(message: String){
        repository.sendMessage(chatData?.id?:-1, message, SEND_MESSAGE_CODE)
    }

    private var looper: CoroutineScope?  = null

    fun startLooper(){
        looper = CoroutineScope(Dispatchers.IO)
        looper?.async {
            while (true) {
                delay(2000)
                repository.getChat(LOOP_REQUEST_ALL_CHAT_CODE)
            }
        }
    }

    fun closeLooper(){
        looper?.cancel()
    }

    inner class RepositoryListener: ChatRepositoryListener{
        override fun onChatResponse(response: ChatResponse, code: Int) {
            if(response.chatData != null) {
                chatData = response.chatData
                view.showChat(response.chatData)
            }
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
            if(code != SEND_MESSAGE_CODE && code != LOOP_REQUEST_ALL_CHAT_CODE)
                view.showLoading()
        }

        override fun noInternet(code: Int?) {
            view.showNoNetwork()
            when(code){
                SEND_MESSAGE_CODE -> {
                    view.showToast("?????? ?????????????????????? ?? ??????????????????")
                }
                else -> {
                    view.showNoNetwork()
                }
            }
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')

            when(code){
                LOOP_REQUEST_ALL_CHAT_CODE -> {
                    view.showToast("$mess - $errorCode")
                }
                SEND_MESSAGE_CODE -> {
                    view.showToast("$mess - $errorCode")
                }
                else -> {
                    view.showError(mess, errorCode)
                }
            }
        }
    }
}