package VBllc.user.homehotel.Main.ui.guest.Gastronomy

import VBllc.user.homehotel.API.DataResponse.FoodData
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.BaseAdapter
import android.widget.LinearLayout
import androidx.cardview.widget.CardView

class GastronomyFragment : Fragment() {

    var settle: SettleResponse.SettleData? = null
    private lateinit var grid: LinearLayout

    val testList = mutableListOf<FoodData>(
        FoodData(1),
        FoodData(1),
        FoodData(1),
        FoodData(1),
        FoodData(1),
        FoodData(1),
        FoodData(1)
    )

    fun initViews(root: View, layoutInflater: LayoutInflater){
        grid = root.findViewById(R.id.foodList)
        grid.removeAllViewsInLayout()
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        val param = grid.layoutParams
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gastronomy, container, false)
        initViews(root, inflater)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(settle: SettleResponse.SettleData) =
            GastronomyFragment().apply {
                this.settle = settle
            }
    }

    inner class MGridAdapter(val data: MutableList<FoodData>): BaseAdapter() {


        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val root = LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null)
            return root
        }

        override fun getItem(position: Int): Any = data[position]


        override fun getItemId(position: Int): Long = position.toLong()


        override fun getCount(): Int = data.count()

    }
}