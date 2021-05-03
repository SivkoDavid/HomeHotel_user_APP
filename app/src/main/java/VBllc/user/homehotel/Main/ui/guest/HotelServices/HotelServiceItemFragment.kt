package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse.HotelServiceData
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.HotelServicesView
import android.widget.Toast
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class HotelServiceItemFragment : Fragment(), HotelServicesView {

    private var recucler: RecyclerView? = null
    var settle: SettleResponse.SettleData? = null
    private val presenter = HotelServicesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.settle = settle
    }

    var servicesList: MutableList<HotelServiceData> = mutableListOf()
        set(value) {
            field?.clear()
            field?.addAll(value?: listOf())
            recucler?.adapter?.notifyDataSetChanged()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_h_service_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            recucler = view
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = HotelServiceItemRecyclerViewAdapter(servicesList)
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newInstance(data: SettleResponse.SettleData): Fragment {
            val args = Bundle()

            val fragment = HotelServiceItemFragment()
            fragment.settle = data
            return fragment
        }

    }

    override fun showServices(data: List<HotelServiceData>) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                servicesList = data.toMutableList()
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                Toast.makeText(requireContext(), "Ошибка", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                Toast.makeText(requireContext(), "Загрузка", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                Toast.makeText(requireContext(), "Нет интернета", Toast.LENGTH_LONG).show()
            }
        }
    }

}

