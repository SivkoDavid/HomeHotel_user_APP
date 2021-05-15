package VBllc.user.homehotel.Main.ui.guest.Cleaning

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.Main.ui.guest.HotelServices.HotelServiceItemFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.OrderCleaningView
import android.widget.Button
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class OrderCleaningFragment : Fragment(), OrderCleaningView {

    private lateinit var dateInput: DatePicker
    private lateinit var timeInput: TimePicker
    private lateinit var orderBtn: Button
    private var presenter: OrderCleaningPresenter = OrderCleaningPresenter(this)


    fun initViews(root: View){
        dateInput = root.findViewById(R.id.dateInput)
        timeInput = root.findViewById(R.id.timeInput)
        orderBtn = root.findViewById(R.id.orderButton)

        timeInput.setIs24HourView(true)
        orderBtn.setOnClickListener {
            val date =
                    "${dateInput.year}-${dateInput.month+1}-${dateInput.dayOfMonth} " +
                            "${timeInput.hour}:${timeInput.minute}"
            presenter.sendCleaningOrder(date)
        }
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
            fragment.presenter.settle = data
            return fragment
        }

    }

    override fun backToMenu() {
        CoroutineScope(Dispatchers.Main).launch {
            this@OrderCleaningFragment.whenStarted {
                fragmentManager?.popBackStack()
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@OrderCleaningFragment.whenStarted {

            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@OrderCleaningFragment.whenStarted {

            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@OrderCleaningFragment.whenStarted {

            }
        }
    }

    override fun showToast(text: String, length: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@OrderCleaningFragment.whenStarted {
                Toast.makeText(requireContext(), text, length).show()
            }
        }
    }
}