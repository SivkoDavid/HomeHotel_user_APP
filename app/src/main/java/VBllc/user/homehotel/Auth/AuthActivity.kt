package VBllc.user.homehotel.Auth

import VBllc.user.homehotel.AdditionalComponents.DialogWindows.LoadingDialog
import VBllc.user.homehotel.Auth.Login.LoginFragment
import VBllc.user.homehotel.Auth.Login.LoginFragmentListener
import VBllc.user.homehotel.Auth.Registration.RegistrationFragment
import VBllc.user.homehotel.Auth.Registration.RegistrationFragmentListener
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.AuthView
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.security.auth.login.LoginException

class AuthActivity : AppCompatActivity(), AuthView {

    private lateinit var pager: ViewPager
    private lateinit var loginFragment: LoginFragment
    private lateinit var registrationFragment: RegistrationFragment
    private lateinit var loadingDialog: LoadingDialog
    private var pAdapter: AuthPagerAdapter? = null
    private val authPresenter: AuthPresenter = AuthPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initialFragments()
        pAdapter = AuthPagerAdapter(listOf(loginFragment, registrationFragment), supportFragmentManager)
        initialAllViews()
    }

    override fun onStart() {
        super.onStart()
        //loadingDialog.show(supportFragmentManager,"")
    }

    private fun initialAllViews(){
        pager = findViewById(R.id.auth_pager)
        loadingDialog = LoadingDialog(supportFragmentManager)
        pager.adapter  = pAdapter
    }

    private fun initialFragments(){
        loginFragment = LoginFragment()
        registrationFragment = RegistrationFragment()

        loginFragment.setListener(object : LoginFragmentListener{
            override fun onLoginClick(login: String, password: String) {
                authPresenter.login(login, password)
            }

            override fun onGoToReristrationClick() {
                pager.setCurrentItem(1, true)
            }
        })

        registrationFragment.setListener(object: RegistrationFragmentListener{
            override fun registrationClick(name: String, surname: String, email: String, pass: String) {
                authPresenter.registration(name, surname, email, pass)
            }
        })
    }

    private class AuthPagerAdapter(val fragments:List<Fragment>, fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return 2
        }

    }

    override fun showAutorise() {
        CoroutineScope(Dispatchers.Main).launch {
            loginFragment.printError("")
            registrationFragment.printError("")
            loadingDialog.close()
            Toast.makeText(applicationContext, "Вы успешно авторизированы", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showErrorAutorise(errorMessage: String, errorCode: Int, typeError: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            when (typeError) {
                AuthView.LOGIN_ERROR -> {
                    loginFragment.printError(errorMessage)
                }
                AuthView.REGISTRATION_ERROR -> {
                    registrationFragment.printError(errorMessage)
                }
            }
            loadingDialog.close()
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) { }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            loadingDialog.open()
            loginFragment.printError("")
            registrationFragment.printError("")
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            loadingDialog.close()
            loginFragment.printError("Нет подключения к интернету")
            registrationFragment.printError("Нет подключения к интернету")
        }
    }

    override fun onBackPressed() {
        if(pager.currentItem == 1 )
            pager.setCurrentItem(0, true)
        else
            super.onBackPressed()
    }

}