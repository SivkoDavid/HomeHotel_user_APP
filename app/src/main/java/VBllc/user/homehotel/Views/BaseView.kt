package VBllc.user.homehotel.Views

import VBllc.user.homehotel.App.HomeHotelApp
import android.widget.Toast

interface BaseView {
    fun showError(errorMessage: String, errorCode: Int)

    fun showLoading()


    fun showNoNetwork()

    fun showToast(text: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(HomeHotelApp.appContext, text, length).show()
    }
}