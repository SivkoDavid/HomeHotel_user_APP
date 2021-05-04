package VBllc.user.homehotel.Main.ui.guest.Gastronomy

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.Main.ui.guest.Hygiene.HygieneFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R

class GastronomyFragment : Fragment() {

    var settle: SettleResponse.SettleData? = null

    fun initViews(root: View){

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gastronomy, container, false)
        initViews(root)
        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(settle: SettleResponse.SettleData) =
            GastronomyFragment().apply {
                this.settle = settle
            }
    }
}