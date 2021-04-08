package VBllc.user.homehotel.Main.ui.progile

import VBllc.user.homehotel.API.DataResponse.UserInfoResponse
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
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(), ProfileView {

    private var loginLayout: View? = null
    private var noLoginLayout: View? = null
    private var loginButton: Button? = null
    private var regButton: Button? = null
    private var logoutButton: Button? = null
    private var menu1: Button? = null
    private var name: TextView? = null
    private var email: TextView? = null
    private var avatar: ImageView? = null

    private lateinit var presenter: ProfilePresenter


    private fun initViews(root:View){
        loginLayout = root.findViewById(R.id.loginLayout)
        noLoginLayout = root.findViewById(R.id.noLoginLayout)
        loginButton = root.findViewById(R.id.buttonLogin)
        logoutButton = root.findViewById(R.id.buttonLogout)
        regButton = root.findViewById(R.id.buttonRegister)
        name = root.findViewById(R.id.name)
        email = root.findViewById(R.id.email)

        loginButton?.setOnClickListener { loginClick() }
        logoutButton?.setOnClickListener { presenter.logout() }
        regButton?.setOnClickListener { registrationClick() }
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

    override fun showLoginView(userInfo: UserInfoResponse.UserInfoData?) {
        CoroutineScope(Dispatchers.Main).launch {
            loginLayout?.visibility = View.VISIBLE
            noLoginLayout?.visibility = View.GONE

            if(userInfo!=null){
                name?.text = "${userInfo.second_name?:""} ${userInfo.name} ${userInfo.third_name?:""}"
                email?.text = userInfo.email
            }
        }
    }

    override fun showNoLoginView() {
        CoroutineScope(Dispatchers.Main).launch {
            loginLayout?.visibility = View.GONE
            noLoginLayout?.visibility = View.VISIBLE
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

    private fun loginClick(){
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.putExtra(AuthActivity.INTENT_EXTRA_ACTION_FIELD, AuthActivity.INTENT_ACTION_LOGIN)
        startActivity(intent)
    }

    private fun registrationClick(){
        val intent = Intent(requireContext(), AuthActivity::class.java)
        intent.putExtra(AuthActivity.INTENT_EXTRA_ACTION_FIELD, AuthActivity.INTENT_ACTION_REGISTRATION)
        startActivity(intent)
    }
}