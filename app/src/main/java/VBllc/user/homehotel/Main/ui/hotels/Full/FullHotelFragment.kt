package VBllc.user.homehotel.Main.ui.hotels.Full

import VBllc.user.homehotel.API.DataResponse.HotelsPesponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.TextView

class FullHotelFragment : Fragment() {

    private var data: HotelsPesponse.HotelData? = null

    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var address: TextView

    private fun initViews(root: View){
        name = root.findViewById(R.id.hotelName)
        phone = root.findViewById(R.id.hotelPhone)
        address = root.findViewById(R.id.hotelAddress)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_full_hotel, container, false)
        initViews(root)
        return root
    }

    fun printInfo(hotel: HotelsPesponse.HotelData){
        name.text = hotel.name
        address.text = hotel.address
        phone.text = hotel.main_phone
    }

    fun newInstance(hotel:HotelsPesponse.HotelData): FullHotelFragment{
        printInfo(hotel)
        return this
    }
}