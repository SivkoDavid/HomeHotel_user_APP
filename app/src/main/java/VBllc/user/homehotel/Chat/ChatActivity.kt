package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.ChatView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter = ChatPresenter(this)
    private lateinit var messagesList: RecyclerView

    var chatData: ChatResponse.ChatData? = null
        set(value) {
            if(field == null && value != null) {
                field = value
                messagesList.adapter = MessagesRecuclerAdapter(chatData?.messages!!)
                //messagesList.layoutManager?.scrollToPosition(chatData?.messages!!.size-1)
            }
            else
            {
                field?.messages?.clear()
                field?.messages?.addAll(field?.messages?: mutableListOf())

            }
            messagesList.adapter?.notifyDataSetChanged()
        }

    fun initViews(){
        messagesList = findViewById(R.id.rec_maesslist)
        val lManager = GridLayoutManager(this, 1)
        lManager.reverseLayout = true
        messagesList.layoutManager = lManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initViews()
    }





    override fun showChat(chat: ChatResponse.ChatData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@ChatActivity.whenStarted {
                chatData = chat
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