package VBllc.user.homehotel.Main

import VBllc.user.homehotel.Main.ui.guest.GuestFragment
import VBllc.user.homehotel.Main.ui.hotels.HotelsFragment
import VBllc.user.homehotel.Main.ui.profile.ProfileFragment
import VBllc.user.homehotel.R
import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    lateinit var guestFragment: GuestFragment
    lateinit var hotelsFragment: HotelsFragment
    lateinit var profileFragment: ProfileFragment
    lateinit var mFragmentManger: FragmentManager
    lateinit var navView: BottomNavigationView


    lateinit var navMap: Map<Int, Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        guestFragment = GuestFragment()
        hotelsFragment = HotelsFragment()
        profileFragment = ProfileFragment()


        navMap = mapOf(
                R.id.navigation_guest to guestFragment,
                R.id.navigation_hotels to hotelsFragment,
                R.id.navigation_profile to profileFragment
        )

        mFragmentManger = supportFragmentManager


        mFragmentManger.beginTransaction().add(R.id.nav_host_fragment, hotelsFragment).commit()
        mFragmentManger.beginTransaction().add(R.id.nav_host_fragment, profileFragment).commit()
        mFragmentManger.beginTransaction().add(R.id.nav_host_fragment, guestFragment).commit()

        navView.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.navigation_guest -> {
                    showFragment(it.itemId)
                }

                R.id.navigation_hotels -> {
                    showFragment(it.itemId)
                }

                R.id.navigation_profile -> {
                    showFragment(it.itemId)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
        showFragment(R.id.navigation_guest)
    }


    fun showFragment(itemMenu: Int){
        val transaction = mFragmentManger.beginTransaction()
        for(item in navMap){
            if(item.key != itemMenu)
                transaction.hide(item.value)
            else {
                transaction.show(item.value)
            }
        }

        transaction.commit()
    }

    fun getShowedMenuItemId(): Int{
        var result = R.id.navigation_guest
        for(it in navMap){
            if(!it.value.isHidden)
                result = it.key
        }
        return result
    }

    override fun onBackPressed() {
        if(getShowedMenuItemId() == R.id.navigation_guest){
            if(guestFragment.childFragmentManager.backStackEntryCount > 0)
                guestFragment.childFragmentManager.popBackStack()
            else {
                val startMain = Intent(Intent.ACTION_MAIN)
                startMain.addCategory(Intent.CATEGORY_HOME)
                startMain.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(startMain)
            }
        }
        else {
            if(getShowedMenuItemId() == R.id.navigation_hotels
                && hotelsFragment.childFragmentManager.backStackEntryCount > 0)
                hotelsFragment.childFragmentManager.popBackStack()
            else
                navView.selectedItemId = R.id.navigation_guest
        }
    }

}