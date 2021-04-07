package VBllc.user.homehotel.AdditionalComponents.DialogWindows

import VBllc.user.homehotel.R
import android.animation.Animator
import android.animation.ValueAnimator
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.forEachIndexed
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class LoadingDialog(
        val _fragmentManager: FragmentManager
) : DialogFragment() {

    var thisOrient:Int = 0;

    var printResult = false

    var isShowing = false
    var lastInstant: Bundle? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        lastInstant = savedInstanceState
        isShowing = savedInstanceState?.getBoolean("dialogIsShow", false)?:true
        printResult = savedInstanceState?.getBoolean("printResult", false)?:true
        return activity?.let {
            thisOrient = activity?.requestedOrientation!!
            activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED)
            val builder = AlertDialog.Builder(it, R.style.Dialog)
            builder.setView(R.layout.loading_dialog)
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onStart() {
        super.onStart()
        backSetUnclickable()
    }

    override fun onResume() {
        super.onResume()
        if(!isShowing){
            dialog?.hide()
        }
    }

    private fun backSetUnclickable(){
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
    }

    fun open(){
        if(!isAdded){
            this.show(_fragmentManager, "")
        }
        else
            dialog?.show()
    }



    fun close(){
        if (dialog?.isShowing?:false) {
            isShowing = false
            dialog!!.hide()
            activity?.setRequestedOrientation(thisOrient)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("dialogIsShow", this.isShowing)
        outState.putBoolean("printResult", this.printResult)
        super.onSaveInstanceState(outState)
    }
}