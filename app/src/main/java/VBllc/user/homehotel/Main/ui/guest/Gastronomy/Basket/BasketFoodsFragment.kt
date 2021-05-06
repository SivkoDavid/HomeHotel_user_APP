package VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket

import VBllc.user.homehotel.Main.ui.guest.Gastronomy.GastronomyPresenter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketFoodsFragment : Fragment() {

    var basked: FoodBasketData? = null
        set(value) {
            field = value
            CoroutineScope(Dispatchers.Main).launch {
                this@BasketFoodsFragment.whenStarted {
                    if(field != null)
                        printInfo(field!!)
                }
            }

        }

    var gastronomyPresenter: GastronomyPresenter? = null

    private lateinit var foodsList: LinearLayout
    private fun initViews(root: View){
        foodsList = root.findViewById(R.id.foods)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_basket_foods, container, false)
        initViews(root)
        return root
    }

    fun printInfo(info: FoodBasketData){
        printList(info.foodList)
    }

    fun printList(foods: List<FoodBasketData.FoodInBid>){
        foodsList.removeAllViewsInLayout()
        foods.forEach {
            foodsList.addView(getFoodCard(it))
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(data: FoodBasketData) =
                BasketFoodsFragment().apply {
                    basked = data
                }
    }

    private fun getFoodCard(food: FoodBasketData.FoodInBid): View{
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.food_in_basket_card, null)
        view.findViewById<TextView>(R.id.name).text = food.food.name?:""
        view.findViewById<TextView>(R.id.count).text = food.count.toString()
        view.findViewById<TextView>(R.id.price).text = ((food.food.price?:0f) * food.count).toString() + " руб."
        view.findViewById<Button>(R.id.plusBtn).setOnClickListener { gastronomyPresenter?.addFoodInBid(food.food) }
        view.findViewById<Button>(R.id.minusBtn).setOnClickListener { gastronomyPresenter?.minusFoodFromBid(food.food) }
        view.findViewById<ImageButton>(R.id.deleteBtn).setOnClickListener { gastronomyPresenter?.deleteFoodFromBid(food) }
        return view
    }
}