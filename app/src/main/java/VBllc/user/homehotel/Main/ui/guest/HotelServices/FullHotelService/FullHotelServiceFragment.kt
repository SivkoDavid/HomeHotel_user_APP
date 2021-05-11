package VBllc.user.homehotel.Main.ui.guest.HotelServices.FullHotelService

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.*
import androidx.lifecycle.whenStarted
import com.google.android.material.textfield.TextInputLayout
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class FullHotelServiceFragment : Fragment() {
    var data: HotelServicesResponse.HotelServiceData? = null
        set(value) {
            field = value
            CoroutineScope(Dispatchers.Main).launch {
                this@FullHotelServiceFragment.whenStarted {
                    if(field != null)
                        printInfo(field!!)
                }
            }
        }

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
    var settle: SettleResponse.SettleData? = null

    private fun initViews(root: View){
        name = root.findViewById(R.id.name)
        price = root.findViewById(R.id.price)
        desc = root.findViewById(R.id.desc)
        categoryLabel = root.findViewById(R.id.categoryLabel)
        labelData = root.findViewById(R.id.labelData)
        labelTime = root.findViewById(R.id.labelTime)
        imageView = root.findViewById(R.id.imageView)
        dateInput = root.findViewById(R.id.dateInput)
        timeInput = root.findViewById(R.id.timeInput)
        timeInput.setIs24HourView(true)
        showFieldsBtn = root.findViewById(R.id.showFieldsBtn)
        sendBtn = root.findViewById(R.id.sendBtn)
        fields = root.findViewById(R.id.fields)
        fields.visibility = View.GONE
        showFieldsBtn.setOnClickListener {
            fields.visibility = View.VISIBLE
            showFieldsBtn.visibility = View.GONE
        }
        sendBtn.setOnClickListener {
            val date =
                    "${dateInput.year}-${dateInput.month}-${dateInput.dayOfMonth} " +
                    "${timeInput.hour}:${timeInput.minute}"
            listener?.onBidCompiled(data?.id?:-1, date)
        }
    }

    private fun printInfo(data: HotelServicesResponse.HotelServiceData){
        name.text = data.name
        var descStr = data.description?:""
        if(descStr.length > 150)
            descStr = descStr.substring(0, 150).substringBeforeLast(' ') + "..."
        desc.text = descStr
        price.text = "${data.price?:""} ${data.price_type?:""}"
        Picasso.get()
            .load(data.picture)
            .placeholder(R.drawable.servise_standsrt_img)
            .error(R.drawable.servise_standsrt_img)
            .into(imageView)
        categoryLabel.text = data.category + " • " + data.subcategory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_full_hotel_service, container, false)
        initViews(root)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(service: HotelServicesResponse.HotelServiceData, settleData: SettleResponse.SettleData?) =
                FullHotelServiceFragment().apply {
                    settle = settleData
                    data = service
                }
    }
}