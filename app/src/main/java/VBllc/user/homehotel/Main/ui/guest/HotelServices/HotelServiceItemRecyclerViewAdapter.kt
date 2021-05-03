package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse.HotelServiceData
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import VBllc.user.homehotel.R
import android.widget.ImageView
import com.squareup.picasso.Picasso

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
        holder.nameView.text = item.name
        var desc = item.description?:""
        if(desc.length > 150)
            desc = desc.substring(0, 150).substringBeforeLast(' ') + "..."
        holder.descView.text = desc
        holder.priceView.text = "${item.price} ${item.price_type}"
        Picasso.get()
                .load(item.picture)
                .placeholder(R.drawable.servise_standsrt_img)
                .error(R.drawable.servise_standsrt_img)
                .into(holder.imageView);
        holder.nameView.text = item.name
        holder.cardView.setOnClickListener {  }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.image)
        val nameView: TextView = view.findViewById(R.id.name)
        val descView: TextView = view.findViewById(R.id.desc)
        val priceView: TextView = view.findViewById(R.id.price)
        val cardView: View = view.findViewById(R.id.card)
    }
}