package VBllc.user.homehotel.Main.ui.guest.Gastronomy

import VBllc.user.homehotel.API.DataResponse.FoodData
import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragment
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragmentListener
import VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket.BasketFoodsFragment
import VBllc.user.homehotel.Main.ui.guest.Gastronomy.Basket.FoodBasketData
import VBllc.user.homehotel.Main.ui.guest.HotelServices.FullHotelService.FullHotelServiceFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.GastronomyView
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GastronomyFragment : Fragment(), GastronomyView{

    var settle: SettleResponse.SettleData? = null
    private lateinit var grid: LinearLayout
    private lateinit var typeSelector: Spinner
    private lateinit var foodCount: TextView
    private lateinit var priceSumm: TextView
    private lateinit var sendBtn: Button
    lateinit private var loadingFragment : ProgressFragment
    lateinit private var basketFragment : BasketFoodsFragment
    private val presenter = GastronomyPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.settle = settle
    }



    fun initViews(root: View){
        grid = root.findViewById(R.id.foodList)
        typeSelector = root.findViewById(R.id.type_selector)
        foodCount = root.findViewById(R.id.foodCount)
        priceSumm = root.findViewById(R.id.priceSumm)
        sendBtn = root.findViewById(R.id.sendBtn)
        basketFragment = BasketFoodsFragment()
        loadingFragment = ProgressFragment(parentFragmentManager)
        parentFragmentManager.beginTransaction().add(R.id.fragmentContainer_gastronomy, loadingFragment).commit()
        loadingFragment.listener = object : ProgressFragmentListener {
            override fun buttonReloadClick() {
                presenter.refresh(settle)
            }
        }
        sendBtn.setOnClickListener { presenter.bascketClick() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gastronomy, container, false)
        initViews(root)
        return root
    }

    private fun printFoodList(menu: List<FoodData>){
        grid.removeAllViewsInLayout()
        menu.forEach{
            grid.addView(getFoodCard(it))
        }
    }

    private fun initFoodCategories(categories: Array<String>){
        val adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_layout, R.id.text, categories)
        typeSelector.adapter = adapter
        typeSelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                presenter.categorySelected(adapter.getItem(position))
            }
        }
    }

    private fun printUpBar(foodCount: Int, priceSumm: Float){
        this.foodCount.setText(foodCount.toString())
        this.priceSumm.setText(priceSumm.toString()+" р.")
    }

    fun showBascket(basketData: FoodBasketData){
        basketFragment.basked = basketData
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_gastronomy, basketFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun updateFoodCategories(categories: Array<String>) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                initFoodCategories(categories)
            }
        }
    }

    override fun updateUpBar(foodCount: Int, priceSumm: Float) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                printUpBar(foodCount, priceSumm)
            }
        }
    }


    override fun showFoodMenu(menu: List<FoodData>) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                loadingFragment.hide()
                printFoodList(menu)
            }
        }
    }

    override fun updateBasket(basketData: FoodBasketData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                basketFragment.basked = basketData
            }
        }
    }

    override fun openBasket(basketData: FoodBasketData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                showBascket(basketData)
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                loadingFragment.showStatus(errorMessage, ProgressFragment.ERROR_IMAGE)
            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                loadingFragment.showLoading()
            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@GastronomyFragment.whenStarted {
                loadingFragment.showStatus("Отсутствует подключение к интернету", ProgressFragment.ERROR_IMAGE)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(settle: SettleResponse.SettleData) =
            GastronomyFragment().apply {
                this.settle = settle
            }
    }

    private fun getFoodCard(food: FoodData): View{
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null)
        view.findViewById<TextView>(R.id.name).text = food.name?:""
        view.findViewById<TextView>(R.id.price).text = food.price.toString() + " руб."
        view.findViewById<ImageButton>(R.id.addFoodBtn).setOnClickListener {
            presenter.addFoodInBid(food)
        }
        return view
    }
}