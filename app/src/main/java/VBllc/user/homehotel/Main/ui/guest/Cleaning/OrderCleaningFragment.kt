package VBllc.user.homehotel.Main.ui.guest.Cleaning

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.Main.ui.guest.HotelServices.HotelServiceItemFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker


class OrderCleaningFragment : Fragment() {

    private lateinit var dateInput: DatePicker
    private lateinit var timeInput: TimePicker
    private lateinit var orderBtn: Button

    fun initViews(root: View){
        dateInput = root.findViewById(R.id.dateInput)
        timeInput = root.findViewById(R.id.timeInput)
        orderBtn = root.findViewById(R.id.orderButton)

        timeInput.setIs24HourView(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_order_cleaning, container, false)
        initViews(root)
        return root
    }

    companion object {
        fun newInstance(data: SettleResponse.SettleData): Fragment{
            val args = Bundle()

            val fragment = OrderCleaningFragment()

            return fragment
        }

    }

}