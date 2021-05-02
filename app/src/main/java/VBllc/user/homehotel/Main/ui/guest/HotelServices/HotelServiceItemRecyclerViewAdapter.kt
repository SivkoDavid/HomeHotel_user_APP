package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse.HotelServiceData
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import VBllc.user.homehotel.R

class HotelServiceItemRecyclerViewAdapter(
        private val values: List<HotelServiceData>)
    : RecyclerView.Adapter<HotelServiceItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_h_service_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        //holder.idView.text = item.id.toString()
        //holder.contentView.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val idView: TextView = view.findViewById(R.id.item_number)
        //val contentView: TextView = view.findViewById(R.id.content)
    }
}