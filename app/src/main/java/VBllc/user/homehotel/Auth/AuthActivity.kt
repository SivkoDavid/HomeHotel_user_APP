package VBllc.user.homehotel.Auth

import VBllc.user.homehotel.Auth.Login.LoginFragment
import VBllc.user.homehotel.Auth.Registration.RegistrationFragment
import VBllc.user.homehotel.R
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

class AuthActivity : AppCompatActivity() {

    private lateinit var pager: ViewPager
    private lateinit var loginFragment: LoginFragment
    private lateinit var registrationFragment: RegistrationFragment
    private var pAdapter: AuthPagerAdapter? = null


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
    }

    private class AuthPagerAdapter(val fragments:List<Fragment>, fm: FragmentManager): FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragments.get(position)
        }

        override fun getCount(): Int {
            return 2
        }

    }


}