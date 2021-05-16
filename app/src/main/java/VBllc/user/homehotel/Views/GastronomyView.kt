package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.FoodData
import VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket.FoodBasketData

interface GastronomyView:BaseView {
    fun updateFoodCategories(categories: Array<String>)
    fun updateUpBar(foodCount: Int, priceSumm: Float)
    fun showFoodMenu(menu: List<FoodData>)
    fun openBasket(basketData: FoodBasketData)
    fun updateBasket(basketData: FoodBasketData)
    fun showLoadingDialog()
    fun showOkDialog(message: String)
    fun showErrorDialog(message: String)
}