package VBllc.user.homehotel.Main.ui.hotels

import VBllc.user.homehotel.API.DataResponse.HotelsPesponse
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragment
import VBllc.user.homehotel.AdditionalComponents.ProgressFragment.ProgressFragmentListener
import VBllc.user.homehotel.Main.ui.hotels.Full.FullHotelFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.HotelsView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HotelsFragment : Fragment(), HotelsView{
    private var hotelAdapter: MyHotelItemRecyclerViewAdapter? = null
    private var progressFragment: ProgressFragment? = null
    private var fullHotelFragment: FullHotelFragment? = null

    private lateinit var presenter: HotelsPresenter
    private lateinit var refresher: SwipeRefreshLayout

    var data = mutableListOf<HotelsPesponse.HotelData>()
        set(value) {
            field.clear()
            field.addAll(value)
            hotelAdapter?.notifyDataSetChanged()
        }

    private fun initViews(root: View){
        progressFragment = ProgressFragment(childFragmentManager)
        fullHotelFragment = FullHotelFragment()
        childFragmentManager.beginTransaction()
            .add(R.id.innerFragment, progressFragment!!, "progress")
            .hide(progressFragment!!).commit()
        childFragmentManager?.beginTransaction()
            ?.add(R.id.innerFragment, fullHotelFragment!!, "progress")
            ?.hide(fullHotelFragment!!)?.commit()
        progressFragment?.listener = object : ProgressFragmentListener{
            override fun buttonReloadClick() {
                presenter.refreshHotels()
            }
        }

        refresher = root.findViewById(R.id.swipeRefresh)
        refresher.setOnRefreshListener {
            presenter.refreshHotels()
            refresher.isRefreshing = false
        }
    }

    private val fragments = mutableListOf<Fragment>()

    private fun openFragment(fragment: Fragment){
        if(!fragments.contains(fragment)) {
            fragments.add(fragment)
        }
        val tran = childFragmentManager?.beginTransaction()

        fragments.forEach{
            if(it != fragment){
                tran?.hide(it)
            }
            else
                tran?.show(it)
        }

        tran?.addToBackStack(null)?.commit()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hotels, container, false)

        val list = root.findViewById<RecyclerView>(R.id.listHotel)
        hotelAdapter = MyHotelItemRecyclerViewAdapter(data)
        list.layoutManager = LinearLayoutManager(requireContext())
        list.adapter = hotelAdapter
        presenter = HotelsPresenter(this)
        hotelAdapter?.setCardListener(object : MyHotelItemRecyclerViewAdapter.HotelAdapterListener {
            override fun onHotelCardClick(hotel: HotelsPesponse.HotelData) {
                presenter.hotelClick(hotel)
            }
        })
        initViews(root)
        return root
    }

    override fun showHotelsList(hotels: List<HotelsPesponse.HotelData>) {
        CoroutineScope(Dispatchers.Main).launch {
            data = hotels.toMutableList()
            progressFragment?.hide()
        }
    }

    override fun openHotel(hotel: HotelsPesponse.HotelData) {
        CoroutineScope(Dispatchers.Main).launch {
            if(fullHotelFragment != null)
                openFragment(fullHotelFragment!!.newInstance(hotel))
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            progressFragment?.showStatus(errorMessage, ProgressFragment.ERROR_IMAGE)
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            progressFragment?.showLoading()
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            progressFragment?.showStatus("Нет подключения к интернету", ProgressFragment.ERROR_IMAGE)
        }
    }
}

class MyHotelItemRecyclerViewAdapter(
        private val values: List<HotelsPesponse.HotelData>
) : RecyclerView.Adapter<MyHotelItemRecyclerViewAdapter.ViewHolder>() {

    private var listener: HotelAdapterListener? = null

    fun setCardListener(listener: HotelAdapterListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.hotel_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.name.setText(item.name)
        holder.address.setText(item.main_phone)
        holder.description.setText(item.address)
        holder.card.setOnClickListener{ listener?.onHotelCardClick(item) }
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.hotelName)
        val description: TextView = view.findViewById(R.id.hotelDescription)
        val address: TextView = view.findViewById(R.id.hotelAddress)
        val card: View = view.findViewById(R.id.hotelCard)

        override fun toString(): String {
            return super.toString() + " '" + name.text + "'" + description.text + "'" + address.text + "'"
        }
    }

    interface HotelAdapterListener{
        fun onHotelCardClick(hotel: HotelsPesponse.HotelData)
    }
}