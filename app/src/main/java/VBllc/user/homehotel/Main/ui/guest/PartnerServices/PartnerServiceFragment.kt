package VBllc.user.homehotel.Main.ui.guest.PartnerServices

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

/**
 * A fragment representing a list of Items.
 */
class PartnerServiceFragment : Fragment() {

    private var columnCount = 1

    private var recucler: RecyclerView? = null
    var settle: SettleResponse.SettleData? = null
    lateinit private var loadingFragment : ProgressFragment
    //private val presenter = HotelServicesPresenter(this)

    private fun initViews(root: View){
        loadingFragment = ProgressFragment(parentFragmentManager)
        parentFragmentManager.beginTransaction().add(R.id.fragmentContainer_partnersServices, loadingFragment).commit()
        loadingFragment.listener = object : ProgressFragmentListener {
            override fun buttonReloadClick() {
                //presenter.refresh(settle)
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
        loadingFragment.hide()
        servicesList = listOf<PartnersServicesResponse.PartnerServiceData>(
                PartnersServicesResponse.PartnerServiceData(
                        1,
                        "1",
                        "2",
                        "",
                        "",
                        "",
                        ""
                )).toMutableList()
    }

    companion object {
        fun newInstance(data: SettleResponse.SettleData): Fragment {
            val args = Bundle()

            val fragment = PartnerServiceFragment()
            fragment.settle = data
            return fragment
        }

    }


}