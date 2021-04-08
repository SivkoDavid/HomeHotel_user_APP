package VBllc.user.homehotel.Main.ui.hotels

import VBllc.user.homehotel.API.DataResponse.HotelsPesponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.HotelsView

class HotelsFragment : Fragment(), HotelsView{

    private lateinit var presenter: HotelsPresenter

    private fun initViews(root: View){
        val textView: TextView = root.findViewById(R.id.text_hotels)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hotels, container, false)
        initViews(root)
        presenter = HotelsPresenter(this)
        return root
    }

    override fun showHotelsList(hotels: List<HotelsPesponse.HotelData>) {
        println(">>>>>>>RESPONSE>>>>>>>> "+hotels)
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        println(">>>>>>>>ERROR!!!>>>>>>>> "+errorMessage)
    }

    override fun showLoading() {
        println("--------------LOADING...------------------")
    }

    override fun showNoNetwork() {
        println("--------------NO NETWORK!------------------")
    }
}