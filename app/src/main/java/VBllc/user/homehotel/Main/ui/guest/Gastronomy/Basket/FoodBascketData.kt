package VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket

import VBllc.user.homehotel.API.DataResponse.FoodData

class FoodBasketData(
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
            if(it.food == food){
                it.count ++
                listener?.onBascketDataChanget(this)
                return
            }
        }
        foodList.add(FoodInBid(1, food))
        listener?.onBascketDataChanget(this)
    }

    fun clear(){
        foodList = mutableListOf()
        listener?.onBascketDataChanget(this)
    }

    fun minusFoodFromBasket(foob: FoodInBid){
        if(foob.count > 1)
            foob.count--
        else{
            foodList.remove(foob)
        }
        listener?.onBascketDataChanget(this)
    }

    fun deleteFoodFromBid(foob: FoodInBid){
        foodList.remove(foob)
        listener?.onBascketDataChanget(this)
    }

    fun minusFoodFromBasket(foob: FoodData){
        var foodInBid: FoodInBid? = null
        foodList.forEach {
            if(it.food == foob)
                foodInBid = it
        }
        if(foodInBid != null) {
            minusFoodFromBasket(foodInBid!!)
        }
    }


    fun setOnBascketDataChangetListener(onBascketDataChanget: (data: FoodBasketData) -> Unit){
        listener = object : OnBascketDataChangetListener{
            override fun onBascketDataChanget(data: FoodBasketData) {
                onBascketDataChanget(data)
            }
        }
    }

    var listener: OnBascketDataChangetListener? = null

    interface OnBascketDataChangetListener{
        fun onBascketDataChanget(data: FoodBasketData)
    }
}