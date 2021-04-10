package VBllc.user.homehotel.Main.ui.guest

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.GuestView
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GuestFragment : Fragment(), GuestView {

    private lateinit var settlementLayout: View
    private lateinit var notSettlementLayout: View
    private lateinit var codeInput: TextInputLayout
    private lateinit var sendCodeBtn: Button

    fun initViews(root: View){
        settlementLayout = root.findViewById(R.id.guest_layout)
        notSettlementLayout = root.findViewById(R.id.not_guest_layout)
        codeInput = root.findViewById(R.id.codeInput)
        sendCodeBtn = root.findViewById(R.id.sendCodeBtn)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_guest, container, false)
        initViews(root)
        return root
    }

    override fun onStart() {
        super.onStart()
        showNoGuestMode()
    }

    private fun openGuestMode(){
        settlementLayout.visibility = View.VISIBLE
        notSettlementLayout.visibility = View.GONE
    }

    private fun closeGuestMode(){
        settlementLayout.visibility = View.GONE
        notSettlementLayout.visibility = View.VISIBLE
    }

    override fun showSettlement(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            openGuestMode()
        }
    }

    override fun showNoGuestMode() {
        CoroutineScope(Dispatchers.Main).launch {
            closeGuestMode()
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }
}