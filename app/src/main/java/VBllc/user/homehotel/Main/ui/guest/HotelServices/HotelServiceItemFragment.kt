package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse.HotelServiceData
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragment
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragmentListener
import VBllc.user.homehotel.App.HomeHotelApp
import VBllc.user.homehotel.Main.ui.guest.HotelServices.FullHotelService.FullHotelServiceFragment
import VBllc.user.homehotel.Main.ui.guest.HotelServices.FullHotelService.FullHotelServiceFragmentListener
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
import androidx.fragment.app.FragmentContainer
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
    lateinit private var loadingFragment : ProgressFragment
    private val presenter = HotelServicesPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.settle = settle
    }

    private fun initViews(root: View){
        loadingFragment = ProgressFragment(parentFragmentManager)
        parentFragmentManager.beginTransaction().add(R.id.fragmentContainer_hotelServices, loadingFragment).commit()
        loadingFragment.listener = object : ProgressFragmentListener{
            override fun buttonReloadClick() {
                presenter.refresh(settle)
            }

        }
    }

    var servicesList: MutableList<HotelServiceData> = mutableListOf()
        set(value) {
            field?.clear()
            field?.addAll(value?: listOf())
            recucler?.adapter?.notifyDataSetChanged()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_h_service_item_list, container, false)
        recucler = root.findViewById(R.id.list)
        initViews(root)
        // Set the adapter
        if (recucler != null) {
            with(recucler!!) {
                layoutManager = LinearLayoutManager(context)
                val adap = HotelServiceItemRecyclerViewAdapter(servicesList)
                adapter = adap
                adap.listener = object : HotelServiceItemRecyclerViewAdapter.HotelServiceItemRecyclerListener{
                    override fun onServiceClick(service: HotelServiceData) {
                           openService(service)
                    }
                }
            }
        }
        return root
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

    fun openService(service: HotelServiceData){
        val frag = FullHotelServiceFragment.newInstance(service, settle)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer_hotelServices, frag)
            .addToBackStack(null)
            .commit()
        frag.listener = object : FullHotelServiceFragmentListener{
            override fun onBidCompiled(serviseId: Int, datetime: String) {
                presenter.sendOrder(serviseId, datetime, null)
            }
        }
    }

    override fun showServices(data: List<HotelServiceData>) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                loadingFragment.hide()
                servicesList = data.toMutableList()
            }
        }
    }

    override fun closeFull() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                parentFragmentManager.popBackStackImmediate()
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                loadingFragment.showStatus(errorMessage, ProgressFragment.ERROR_IMAGE)
            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                loadingFragment.showLoading()
            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                loadingFragment.showStatus("Отсутствует подключение к интернету", ProgressFragment.ERROR_IMAGE)
            }
        }
    }

    override fun showToast(text: String, length: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@HotelServiceItemFragment.whenStarted {
                Toast.makeText(requireContext(), text, length).show()
            }
        }
    }

}

