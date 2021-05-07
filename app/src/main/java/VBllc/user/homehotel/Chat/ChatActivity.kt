package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.ChatView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.whenStarted
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatActivity : AppCompatActivity(), ChatView {

    private val presenter = ChatPresenter(this)
    private lateinit var messagesList: RecyclerView
    lateinit var messageInput: EditText
    lateinit var messageSendBtn: ImageButton
    private lateinit var recuclerMessAdapter: MessagesRecuclerAdapter

    var chatData: ChatResponse.ChatData? = null
        set(value) {
            if(field == null && value != null) {
                field = value
                recuclerMessAdapter = MessagesRecuclerAdapter(chatData?.messages!!)
                messagesList.adapter = recuclerMessAdapter
                messagesList.layoutManager?.scrollToPosition(chatData?.messages?.lastIndex?:0)
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
        messageInput = findViewById(R.id.messege_input)
        messageSendBtn = findViewById(R.id.sendBtn)
        val lManager = LinearLayoutManager(this)
        lManager.stackFromEnd = true
        messagesList.layoutManager = lManager

        messageSendBtn.setOnClickListener {
            val mess = messageInput.text.toString()
            messageInput.setText("")
            presenter.sendMessege(mess)
        }

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
                chatData?.messages?.add(message)
                recuclerMessAdapter.notifyItemChanged(chatData?.messages?.lastIndex?:1)
                messagesList.layoutManager?.scrollToPosition(chatData?.messages?.lastIndex?:0)
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
                recuclerMessAdapter.notifyDataSetChanged()
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