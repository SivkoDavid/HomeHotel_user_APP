package VBllc.user.homehotel.Main.ui.guest.Gastronomy

import VBllc.user.homehotel.API.DataResponse.FoodData
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.cardview.widget.CardView

class GastronomyFragment : Fragment() {

    var settle: SettleResponse.SettleData? = null
    private lateinit var grid: LinearLayout
    private lateinit var typeSelector: Spinner

    var types = arrayOf(
        "Холодные напитки",
        "Горячие напитки",
        "Алкоголь",
        "Первые блюда",
        "Вторые блюда",
        "Десерты")

    fun initViews(root: View){
        grid = root.findViewById(R.id.foodList)
        typeSelector = root.findViewById(R.id.type_selector)
        grid.removeAllViewsInLayout()
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))
        grid.addView(LayoutInflater.from(requireContext()).inflate(R.layout.food_card, null))


        typeSelector.adapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_layout, R.id.text, types)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gastronomy, container, false)
        initViews(root)
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