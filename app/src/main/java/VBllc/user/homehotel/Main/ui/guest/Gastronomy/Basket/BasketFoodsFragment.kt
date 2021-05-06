package VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket

import VBllc.user.homehotel.Main.ui.guest.Gastronomy.GastronomyPresenter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
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

    private fun initViews(root: View){

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_basket_foods, container, false)
        initViews(root)
        return root
    }

    fun printInfo(info: FoodBasketData){
        requireView().findViewById<TextView>(R.id.text).text = info?.foodList.toString()
    }



    companion object {
        @JvmStatic
        fun newInstance(data: FoodBasketData) =
                BasketFoodsFragment().apply {
                    basked = data
                }
    }
}