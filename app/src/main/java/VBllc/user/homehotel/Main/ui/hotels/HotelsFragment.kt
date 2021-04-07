package VBllc.user.homehotel.Main.ui.hotels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import VBllc.user.homehotel.R

class HotelsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hotels, container, false)
        val textView: TextView = root.findViewById(R.id.text_hotels)
        textView.text = "Hotels"
        return root
    }
}