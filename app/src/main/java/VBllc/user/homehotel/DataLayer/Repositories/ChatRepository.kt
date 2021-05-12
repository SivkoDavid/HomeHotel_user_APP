package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.API
import VBllc.user.homehotel.API.ApiFactory
import VBllc.user.homehotel.API.DataResponse.ChatResponse
import VBllc.user.homehotel.DataLayer.Preferences.SettlementPreference
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlin.random.Random

class ChatRepository(val listener: ChatRepositoryListener) {

    fun sendMessage(chatId: Int, text: String, code: Int){
        val message = ChatResponse.ChatData.MessageData(
                -1,
                text,
                "",
                -1,
                "",
                ChatResponse.ChatData.MessageData.Statuses.SEND_PROCESS,
                true
        )
        listener.messageStartedSending(message, code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение
                val response = postReq.sendMessageInChat(
                        SettlementPreference.getSettleCode(),
                        UserInfoPreference.token.getToken(),
                        chatId,
                        text
                )

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        message.status = ChatResponse.ChatData.MessageData.Statuses.SENDED
                        message.userId = response.body()!!.message!!.userId
                        message.id = response.body()!!.message!!.id
                        message.text = response.body()!!.message!!.text
                        message.userId = response.body()!!.message!!.userId
                        message.userId = response.body()!!.message!!.userId
                        listener.messageDelivered(response.body()!!.message!!, code)
                    }
                    else {
                        message.status = ChatResponse.ChatData.MessageData.Statuses.ERROR
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            it.value.forEach{
                                errors.add(it)
                            }
                        }
                        listener.onErrors(errors, 200,  code)
                    }
                } else { //Ошибка сервера
                    listener.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }

            } catch (e: Exception) { //Отсутствие интернета
                listener.noInternet()
            }
        }
    }

    fun getChat(code: Int){
        listener.startRequest("getChat", code)
        //Запускаем карутину
        CoroutineScope(Dispatchers.Unconfined).async{
            val apiFactory = ApiFactory()
            //Подготовка интерфейса API
            val postReq: API = apiFactory.createAPIwithCoroutines()

            try { //Если есть интерент соединение
                val response = postReq.getChat(
                    SettlementPreference.getSettleCode(),
                    UserInfoPreference.token.getToken()
                )

                if (response.isSuccessful()) {
                    if(response.body()!!.success) {
                        sortMessIntoMyAndNoMy(response.body()?.chatData?.messages?:mutableListOf())
                        listener.onChatResponse(response.body()!!, code)
                    }
                    else {
                        val errors = mutableListOf<String>()
                        response.body()!!.errors?.forEach{
                            it.value.forEach{
                                errors.add(it)
                            }
                        }
                        listener.onErrors(errors, 200,  code)
                    }
                } else { //Ошибка сервера
                    listener.onErrors(listOf("Ошибка "+response.code()), response.code(), code)
                }
            } catch (e: Exception) { //Отсутствие интернета
                listener.noInternet()
            }
        }
    }

    private fun sortMessIntoMyAndNoMy(messages: MutableList<ChatResponse.ChatData.MessageData>)
            :  MutableList<ChatResponse.ChatData.MessageData>{
        messages.forEach {
            it.isMyMessge = (it.userId == (UserInfoPreference.userInfo.getInfo()?.id?:-1))
        }
        return messages
    }

}