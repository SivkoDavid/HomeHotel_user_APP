package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.random.Random

class ChatRepository(val listener: ChatRepositoryListener) {

    fun getChat(code: Int){
        listener.startRequest("getChat", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение

                delay(1500)
                var messages = mutableListOf<ChatResponse.ChatData.MessageData>()
                for (i in 1 .. 30){
                    val usId = (0..1).random()
                    var mess = ""
                    for(j in 1..(0..20).random()){
                        mess += "слово "
                    }
                    messages.add(ChatResponse.ChatData.MessageData(
                            i,
                            "Сообщение $i $mess",
                            "User $usId",
                            usId,
                            "",
                            ChatResponse.ChatData.MessageData.Statuses.SENDED))
                }
                messages = sortMessIntoMyAndNoMy(messages)
                listener.onChatResponse(ChatResponse(true, ChatResponse.ChatData(messages)), code)

            } catch (e: Exception) { //Отсутствие интернета
                listener.noInternet()
            }
        }
    }

    private fun sortMessIntoMyAndNoMy(messages: MutableList<ChatResponse.ChatData.MessageData>)
            :  MutableList<ChatResponse.ChatData.MessageData>{
        messages.forEach {
            //it.isMyMessge = (it.userId == (UserInfoPreference.userInfo.getInfo()?.id?:-1))
            it.isMyMessge = (it.userId == 1)
        }
        return messages
    }

}