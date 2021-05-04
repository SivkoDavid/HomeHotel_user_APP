package VBllc.user.homehotel.Main.ui.guest.PartnerServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragment
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragmentListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.PartnerServicesView
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class PartnerServiceFragment : Fragment(), PartnerServicesView {

    private var columnCount = 1

    private var recucler: RecyclerView? = null
    var settle: SettleResponse.SettleData? = null
        set(value) {
            field = value
            presenter.settle = field
        }
    lateinit private var loadingFragment : ProgressFragment
    private val presenter = PartnerServicesPresenter(this)

    private fun initViews(root: View){
        loadingFragment = ProgressFragment(parentFragmentManager)
        parentFragmentManager.beginTransaction().add(R.id.fragmentContainer_partnersServices, loadingFragment).commit()
        loadingFragment.listener = object : ProgressFragmentListener {
            override fun buttonReloadClick() {
                presenter.refresh(settle)
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    var servicesList: MutableList<PartnersServicesResponse.PartnerServiceData> = mutableListOf()
        set(value) {
            field?.clear()
            field?.addAll(value?: listOf())
            recucler?.adapter?.notifyDataSetChanged()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_partner_service_list, container, false)
        recucler = root.findViewById(R.id.list)
        initViews(root)
        // Set the adapter
        if (recucler != null) {
            with(recucler!!) {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                adapter = PartnerServiceRecyclerViewAdapter(servicesList)
            }
        }
        return root
    }

    override fun onStart() {
        super.onStart()

    }

    companion object {
        fun newInstance(data: SettleResponse.SettleData): Fragment {
            val fragment = PartnerServiceFragment()
            fragment.settle = data
            return fragment
        }

    }

    override fun showServices(data: List<PartnersServicesResponse.PartnerServiceData>) {
        CoroutineScope(Dispatchers.Main).launch {
            this@PartnerServiceFragment.whenStarted {
                loadingFragment.hide()
                servicesList = data.toMutableList()
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@PartnerServiceFragment.whenStarted {
                loadingFragment.showStatus(errorMessage, ProgressFragment.ERROR_IMAGE)
            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@PartnerServiceFragment.whenStarted {
                loadingFragment.showLoading()
            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@PartnerServiceFragment.whenStarted {
                loadingFragment.showStatus("Отсутствует подключение к интернету", ProgressFragment.ERROR_IMAGE)
            }
        }
    }
}