package VBllc.user.homehotel.Main.ui.guest.PartnerServices

import VBllc.user.homehotel.API.DataResponse.PartnersServicesResponse
import VBllc.user.homehotel.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView


class PartnerServiceRecyclerViewAdapter(
        private val values: List<PartnersServicesResponse.PartnerServiceData>,
        val context: Context)
    : RecyclerView.Adapter<PartnerServiceRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_partner_service, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.name
        holder.button.setOnClickListener {
            val address: Uri = Uri.parse(item.partner_link?:"")
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)

            if (openLinkIntent.resolveActivity(context.getPackageManager()) != null) {
                context.startActivity(openLinkIntent)
            } else {
                Toast.makeText(context, "Не получается перейти на по адресу", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val button: Button = view.findViewById(R.id.button2)
    }
}