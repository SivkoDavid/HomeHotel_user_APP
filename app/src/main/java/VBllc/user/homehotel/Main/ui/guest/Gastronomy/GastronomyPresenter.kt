package VBllc.user.homehotel.Main.ui.guest.Gastronomy

import VBllc.user.homehotel.API.DataResponse.*
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepository
import VBllc.user.homehotel.DataLayer.Repositories.HotelServicesRepositoryListener
import VBllc.user.homehotel.Views.GastronomyView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class GastronomyPresenter(val view: GastronomyView) {

    private val repository = HotelServicesRepository(RepositoryListener())

    private val GET_FOOD_MENU_CODE = 62

    private val foodBid = FoodBid()
    private var lastMenu: List<FoodData> = listOf()

    var settle: SettleResponse.SettleData? = null
        set(value) {
            field = value
            if(field?.filial?.id != null){
                repository.getFoodMenu(field?.filial?.id!!, GET_FOOD_MENU_CODE)
            }
        }

    fun refresh(settle: SettleResponse.SettleData?){
        this.settle = settle
    }

    fun categorySelected(category: String?){
        val _list =  lastMenu.filter { it.category == category }
        view.showFoodMenu(_list)
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
        view.updateUpBar(foodBid.count(), foodBid.getPriceSumm())
    }

    inner class RepositoryListener: HotelServicesRepositoryListener {
        override fun onServicesResponse(response: HotelServicesResponse, code: Int) { }
        override fun onPartnerServicesResponse(response: PartnersServicesResponse, code: Int) { }
        override fun onFoodMenuResponse(response: FoodMenuResponse, code: Int) {
            lastMenu = response.data?: listOf()
            val categories = getCategories(response.data?: listOf())
            view.updateFoodCategories(categories)
            view.updateUpBar(foodBid.count(), foodBid.getPriceSumm())
            val _list =  (response.data?:listOf()).filter { it.category == categories[0] }
            view.showFoodMenu(_list)
        }

        override fun startRequest(name: String, code: Int) {
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

    private class FoodBid(
        var foodList: MutableList<FoodInBid> = mutableListOf<FoodInBid>()
    ){
        data class FoodInBid(
            var count: Int,
            var food: FoodData
        )

        fun count(): Int = foodList.count()

        fun getPriceSumm():Float{
            var summ = 0f
            foodList.forEach {
                summ += it.count * (it.food.price?:0f)
            }
            return summ
        }

        fun addFood(food: FoodData){
            foodList.forEach {
                if(it.food.id == food.id){
                    it.count ++
                    return
                }
            }
            foodList.add(FoodInBid(1, food))
        }
    }
}