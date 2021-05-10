package VBllc.user.homehotel.Main.ui.guest.Hygiene

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R

class HygieneFragment : Fragment() {
    var settle: SettleResponse.SettleData? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hygiene, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance(settle: SettleResponse.SettleData) =
            HygieneFragment().apply {
                this.settle = settle
            }
    }
}