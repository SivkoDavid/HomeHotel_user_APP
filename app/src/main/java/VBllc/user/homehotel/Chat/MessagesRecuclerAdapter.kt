package VBllc.user.homehotel.Chat

import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import VBllc.user.homehotel.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessagesRecuclerAdapter(var items: MutableList<ChatResponse.ChatData.MessageData>?, val callback: Callback? = null) : RecyclerView.Adapter<MessagesRecuclerAdapter.MainHolder>() {

    private val MY_MESSAGE_TYPE = 1
    private val OTHER_MESSAGE_TYPE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        when(viewType){
            MY_MESSAGE_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.my_one_messege, parent, false)
                return MainHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.other_one_message, parent, false)
                return MainHolder(view)
            }
        }
    }



    override fun getItemCount() = items?.size?:0
    override fun onBindViewHolder(holder: MessagesRecuclerAdapter.MainHolder, position: Int) {
        if(items!=null && items!!.count() > position)
            holder.bind(items!![position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textmes = itemView.findViewById<TextView>(R.id.messege_field)
        private val state_mess = itemView.findViewById<ImageView>(R.id.mess_state)
        fun bind(item: ChatResponse.ChatData.MessageData) {
            textmes.text = item.text

            if(state_mess != null) {
                when (item.status) {
                    (ChatResponse.ChatData.MessageData.Statuses.SEND_PROCESS) -> {
                        state_mess.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_success))
                    }
                    (ChatResponse.ChatData.MessageData.Statuses.ERROR) -> {
                        state_mess.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_alert))
                    }
                    (ChatResponse.ChatData.MessageData.Statuses.SENDED) -> {
                        state_mess.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.ic_success))
                    }
                    else -> {
                        state_mess.visibility = View.INVISIBLE
                    }
                }
            }

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback?.onItemClicked(items?.get(adapterPosition))
            }
        }
    }
    interface Callback {
        fun onItemClicked(item: ChatResponse.ChatData.MessageData?)
    }

    override fun getItemViewType(position: Int): Int {
        val it = items?.get(position)
        if(it?.userId == UserInfoPreference.userInfo.getInfo()?.id)
            return MY_MESSAGE_TYPE
        else
            return OTHER_MESSAGE_TYPE
    }



}