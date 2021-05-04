package VBllc.user.homehotel.Main.ui.guest.HotelServices.FullHotelService

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class FullHotelServiceFragment : Fragment() {
    var data: HotelServicesResponse.HotelServiceData? = null

    private lateinit var nameView: TextView
    private lateinit var descView: TextView
    private lateinit var categoryView: TextView
    private lateinit var imageView: ImageView
    private lateinit var sendButton: Button

    var listener: FullHotelServiceFragmentListener? = null

    private fun initViews(root: View){
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_full_hotel_service, container, false)
        initViews(root)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(service: HotelServicesResponse.HotelServiceData) =
                FullHotelServiceFragment().apply {
                    data = service
                }
    }
}