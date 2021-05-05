package VBllc.user.homehotel.Views

import VBllc.user.homehotel.API.DataResponse.FoodData

interface GastronomyView:BaseView {
    fun updateFoodCategories(categories: Array<String>)
    fun updateUpBar(foodCount: Int, priceSumm: Float)
    fun showFoodMenu(menu: List<FoodData>)
}