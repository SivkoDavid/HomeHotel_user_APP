package VBllc.user.homehotel.Main.ui.guest.PartnerServices

import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import VBllc.user.homehotel.R


class PartnerServiceRecyclerViewAdapter(
        private val values: List<PartnersServicesResponse.PartnerServiceData>)
    : RecyclerView.Adapter<PartnerServiceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_partner_service, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.name
        holder.descriptView.text = item.description
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val descriptView: TextView = view.findViewById(R.id.desc)

    }
}