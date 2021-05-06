package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.ChatView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter = ChatPresenter(this)
    private lateinit var messagesList: RecyclerView

    var chatData: ChatResponse.ChatData? = null

    fun initViews(){
        messagesList = findViewById(R.id.rec_maesslist)
        messagesList.adapter = MessagesRecuclerAdapter(chatData?.messages)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initViews()
    }





    override fun showChat(chat: ChatResponse.ChatData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun updateChat(chat: ChatResponse.ChatData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun addInCancelChat(mesages: List<ChatResponse.ChatData.MessageData>) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun addMessage(message: ChatResponse.ChatData.MessageData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun removeMessage(message: ChatResponse.ChatData.MessageData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun updateMessage(message: ChatResponse.ChatData.MessageData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {

            }
        }
    }
}