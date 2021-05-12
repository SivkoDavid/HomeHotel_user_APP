package VBllc.user.homehotel.Main.ui.guest.Gastronomy

import VBllc.user.homehotel.API.DataResponse.*
import VBllc.user.homehotel.DataLayer.Preferences.SettlementPreference
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket.FoodBasketData
import VBllc.user.homehotel.Views.GastronomyView
import android.widget.Toast

class GastronomyPresenter(val view: GastronomyView) {

    private val repository = HotelServicesRepository(RepositoryListener())

    private val GET_FOOD_MENU_CODE = 62
    private val SEND_FOOD_BID_CODE = 63

    private val foodBid = FoodBasketData()
    private var lastMenu: List<FoodData> = listOf()

    init {
        foodBid.setOnBascketDataChangetListener{
            view.updateUpBar(it.count(), it.getPriceSumm())
            view.updateBasket(it)
        }
    }

    var settle: SettleResponse.SettleData? = null
        set(value) {
            field = value
            if(SettlementPreference.getSettleCode().isNotEmpty()){
                repository.getFoodMenu(SettlementPreference.getSettleCode(), GET_FOOD_MENU_CODE)
            }
        }

    fun refresh(settle: SettleResponse.SettleData?){
        this.settle = settle
    }

    fun categorySelected(category: String?){
        val _list =  lastMenu.filter { it.category == category }
        view.showFoodMenu(_list)
    }

    fun bascketClick(){
        view.openBasket(foodBid)
    }

    private fun getCategories(list: List<FoodData>): Array<String>{
        val result = mutableListOf<String>()
        list.forEach {
            if(!result.contains(it.category))
                result.add(it.category?:"")
        }
        return result.toTypedArray()
    }

    fun addFoodInBid(food: FoodData){
        foodBid.addFood(food)
        view.showToast("${food?.name?:"Блюдо"} добавлено в корзину")
    }

    fun minusFoodFromBid(food: FoodData){
        foodBid.minusFoodFromBasket(food)
        view.showToast("${food?.name?:"Блюдо"} убрано из корзины")
    }

    fun deleteFoodFromBid(food: FoodBasketData.FoodInBid){
        foodBid.deleteFoodFromBid(food)
        view.showToast("${food.food?.name?:"Блюдо"} удфалено из корзины")
    }

    fun sendFoodsBid(){
        val foodIdList = mutableListOf<Int>()
        foodBid.foodList.forEach {
            for(i in 1..it.count)
                foodIdList.add(it.food.id)
        }
        repository.sendOrder(settle?.uid?:"", null, null, foodIdList, SEND_FOOD_BID_CODE)
    }

    inner class RepositoryListener: HotelServicesRepositoryListener {
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) { }
        override fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int) { }
        override fun onSendOrderResponse(response: SendOrderResponse, code: Int) {
            foodBid.clear()
            view.showToast("Еда заказана", Toast.LENGTH_LONG)
        }

        override fun onFoodMenuResponse(response: FoodMenuResponse, code: Int) {
            lastMenu = response.data?: listOf()
            val categories = getCategories(response.data?: listOf())
            view.updateFoodCategories(categories)
            view.updateUpBar(foodBid.count(), foodBid.getPriceSumm())
            val _list =  (response.data?:listOf()).filter { it.category == categories[0] }
            view.showFoodMenu(_list)
        }

        override fun startRequest(name: String, code: Int) {
            if(code != SEND_FOOD_BID_CODE)
                view.showLoading()
        }

        override fun noInternet(code: Int?) {
            view.showNoNetwork()
        }

        override fun onErrors(errorMessages: List<String>, errorCode: Int, code: Int) {
            var mess = ""
            errorMessages.forEach{
                mess += it+"\n"
            }
            mess = mess.substringBeforeLast('\n')
            view.showError(mess, errorCode)
        }
    }


}