package VBllc.user.homehotel.Main.ui.guest.HotelServices.FullHotelService

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.*
import com.google.android.material.textfield.TextInputLayout

class FullHotelServiceFragment : Fragment() {
    var data: HotelServicesResponse.HotelServiceData? = null

    private lateinit var name: TextView
    private lateinit var price: TextView
    private lateinit var desc: TextView
    private lateinit var categoryLabel: TextView
    private lateinit var labelData: TextView
    private lateinit var labelTime: TextView
    private lateinit var labelCount: TextView
    private lateinit var labelCount2: TextView
    private lateinit var imageView: ImageView
    private lateinit var dateInput: DatePicker
    private lateinit var timeInput: TimePicker
    private lateinit var countInput: TextInputLayout
    private lateinit var showFieldsBtn: Button
    private lateinit var sendBtn: Button
    private lateinit var fields: View

    var listener: FullHotelServiceFragmentListener? = null

    private fun initViews(root: View){
        name = root.findViewById(R.id.name)
        price = root.findViewById(R.id.price)
        desc = root.findViewById(R.id.desc)
        categoryLabel = root.findViewById(R.id.categoryLabel)
        labelData = root.findViewById(R.id.labelData)
        labelTime = root.findViewById(R.id.labelTime)
        labelCount = root.findViewById(R.id.labelCount)
        labelCount2 = root.findViewById(R.id.labelCount2)
        imageView = root.findViewById(R.id.imageView)
        dateInput = root.findViewById(R.id.dateInput)
        timeInput = root.findViewById(R.id.timeInput)
        countInput = root.findViewById(R.id.countInput)
        showFieldsBtn = root.findViewById(R.id.showFieldsBtn)
        sendBtn = root.findViewById(R.id.sendBtn)
        fields = root.findViewById(R.id.fields)
        fields.visibility = View.GONE
        showFieldsBtn.setOnClickListener {
            fields.visibility = View.VISIBLE
            showFieldsBtn.visibility = View.GONE
        }
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