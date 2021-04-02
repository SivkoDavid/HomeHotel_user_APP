package VBllc.user.homehotel.Auth

import VBllc.user.homehotel.Auth.Login.LoginFragment
import VBllc.user.homehotel.Auth.Login.LoginFragmentListener
import VBllc.user.homehotel.Auth.Registration.RegistrationFragment
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Views.AuthView
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import javax.security.auth.login.LoginException

class AuthActivity : AppCompatActivity(), AuthView {

    private lateinit var pager: ViewPager
    private lateinit var loginFragment: LoginFragment
    private lateinit var registrationFragment: RegistrationFragment
    private var pAdapter: AuthPagerAdapter? = null
    private val authPresenter: AuthPresenter = AuthPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        initialFragments()
        pAdapter = AuthPagerAdapter(listOf(loginFragment, registrationFragment), supportFragmentManager)
        initialAllViews()
    }

    private fun initialAllViews(){
        pager = findViewById(R.id.auth_pager)

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
        println(">>>>>>>>>>>>>>>>>>>>>> AUTORISE SUCCESS <<<<<<<<<<<<<<<<<<<<<<<")
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        println(">>>>>>>>>>>>>>>>>>>>>> <$errorCode $errorMessage> <<<<<<<<<<<<<<<<<<<<<<<")
    }

    override fun showLoading() {
        println(">>>>>>>>>>>>>>>>>>>>>> START LOADING <<<<<<<<<<<<<<<<<<<<<<<")
    }

    override fun showNoNetwork() {
        println(">>>>>>>>>>>>>>>>>>>>>> NO INTERNET!!! <<<<<<<<<<<<<<<<<<<<<<<")
    }

    override fun onBackPressed() {
        if(pager.currentItem == 1 )
            pager.setCurrentItem(0, true)
        else
            super.onBackPressed()
    }

}