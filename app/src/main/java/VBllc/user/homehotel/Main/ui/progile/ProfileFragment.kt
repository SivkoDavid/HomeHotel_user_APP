package VBllc.user.homehotel.Main.ui.progile

import VBllc.user.homehotel.Auth.AuthActivity
import VBllc.user.homehotel.Main.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.ProfileView
import android.content.Intent
import android.widget.Button

class ProfileFragment : Fragment(), ProfileView {

    private var loginLayout: View? = null
    private var noLoginLayout: View? = null
    private var loginButton: Button? = null
    private var logoutButton: Button? = null

    private lateinit var presenter: ProfilePresenter


    private fun initViews(root:View){
        loginLayout = root.findViewById(R.id.loginLayout)
        noLoginLayout = root.findViewById(R.id.noLoginLayout)
        loginButton = root.findViewById(R.id.buttonLogin)
        logoutButton = root.findViewById(R.id.buttonLogout)

        loginButton?.setOnClickListener { loginClick() }
        logoutButton?.setOnClickListener { presenter.logout() }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        initViews(root)
        presenter = ProfilePresenter(this)
        return root
    }

    override fun showLoginView() {
        loginLayout?.visibility = View.VISIBLE
        noLoginLayout?.visibility = View.GONE
    }

    override fun showNoLoginView() {
        loginLayout?.visibility = View.GONE
        noLoginLayout?.visibility = View.VISIBLE
    }


    override fun showError(errorMessage: String, errorCode: Int) {

    }

    override fun showLoading() {

    }

    override fun showNoNetwork() {

    }

    private fun loginClick(){
        val intent = Intent(requireContext(), AuthActivity::class.java)
        startActivity(intent)
    }
}