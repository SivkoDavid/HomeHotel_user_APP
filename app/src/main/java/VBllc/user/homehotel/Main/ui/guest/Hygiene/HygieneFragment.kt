package VBllc.user.homehotel.Main.ui.guest.Hygiene

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.OrderCleaningView
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HygieneFragment : Fragment(), OrderCleaningView {
    var settleData: SettleResponse.SettleData? = null
        set(value) {
            field = value
            presenter.settle = value
        }

    private lateinit var timeInput: TimePicker
    private lateinit var sendBtn: Button
    private val presenter = OrderHyegenePresenter(this)

    fun initViews(root: View){
        timeInput = root.findViewById(R.id.timeInput)
        timeInput.setIs24HourView(true)
        sendBtn = root.findViewById(R.id.orderButton)
        sendBtn.setOnClickListener {
            val dateNow = Calendar.getInstance().time
            val date =
                    "${dateNow.year}-${dateNow.month}-${dateNow.day} " +
                            "${timeInput.hour}:${timeInput.minute}"
            presenter.sendCleaningOrder(date)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hygiene, container, false)
        initViews(root)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(settle: SettleResponse.SettleData) =
            HygieneFragment().apply {
                settleData = settle
            }
    }

    override fun backToMenu() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HygieneFragment.whenStarted {
                fragmentManager?.popBackStack()
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HygieneFragment.whenStarted {
                Toast.makeText(requireContext(), "Ошибка: $errorMessage", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HygieneFragment.whenStarted {
                Toast.makeText(requireContext(), "Загрузка", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HygieneFragment.whenStarted {
                Toast.makeText(requireContext(), "Нет подключения к интернету", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showToast(text: String, length: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HygieneFragment.whenStarted {
                Toast.makeText(requireContext(), text, length).show()
            }
        }
    }
}