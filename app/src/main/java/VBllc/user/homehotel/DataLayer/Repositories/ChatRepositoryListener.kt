package VBllc.user.homehotel.DataLayer.Repositories

import VBllc.user.homehotel.API.DataResponse.ChatResponse

interface ChatRepositoryListener:BaseRepositoryListener {
    fun onChatResponse(response: ChatResponse, code: Int)
}