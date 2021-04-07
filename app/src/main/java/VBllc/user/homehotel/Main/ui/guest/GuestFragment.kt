package VBllc.user.homehotel.Main.ui.guest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import VBllc.user.homehotel.R

class GuestFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_guest, container, false)
        val textView: TextView = root.findViewById(R.id.text_guest)
        textView.setText("Guest")
        return root
    }
}