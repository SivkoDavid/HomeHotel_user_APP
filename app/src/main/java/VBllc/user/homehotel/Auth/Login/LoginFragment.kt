package VBllc.user.homehotel.Auth.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var loginInput: TextInputLayout
    private lateinit var passInput: TextInputLayout
    private lateinit var loginButton: Button
    private lateinit var goToRegButton: Button
    private lateinit var errorLabel: TextView

    private fun initViews(mainView: View){
        loginInput = mainView.findViewById(R.id.loginInput)
        passInput = mainView.findViewById(R.id.passInput)
        loginButton = mainView.findViewById(R.id.loginBtn)
        goToRegButton = mainView.findViewById(R.id.toRegBtn)
        errorLabel = mainView.findViewById(R.id.errorLabel)

        loginButton.setOnClickListener{ loginClick() }
        goToRegButton.setOnClickListener{ listener?.onGoToReristrationClick() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        initViews(view)
        return view
    }

    private var listener: LoginFragmentListener? = null

    fun setListener(listener: LoginFragmentListener){
        this.listener = listener
    }

    fun newInstance():LoginFragment {
        return  this
    }

    fun printError(error: String){
        if(error == "")
            errorLabel.visibility = View.GONE
        else {
            errorLabel.visibility = View.VISIBLE
            errorLabel.setText(error)
        }
    }


    private fun loginClick(){
        val login = loginInput.editText?.text?.toString()?:""
        val pass = passInput.editText?.text?.toString()?:""
        listener?.onLoginClick(login, pass)
    }
}