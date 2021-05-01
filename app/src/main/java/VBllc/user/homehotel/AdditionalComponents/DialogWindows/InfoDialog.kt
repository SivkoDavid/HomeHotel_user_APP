package VBllc.user.homehotel.AdditionalComponents.DialogWindows

import VBllc.user.homehotel.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class InfoDialog(
        val _fragmentManager: FragmentManager,
        val _teg: String = "dialog_1",
        var message: String = "Идет обработка запроса"
) : DialogFragment() {


    var thisOrient:Int = 0;
    val errorImg  = R.drawable.ic_alert
    val okImg = R.drawable.ic_success

    var printResult = false

    var isShowing = false
    lateinit var image: ImageView
    lateinit var text: TextView
    lateinit var button: Button
    lateinit var progress: ProgressBar
    var frame: LinearLayout? = null
    var lastInstant: Bundle? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        lastInstant = savedInstanceState
        isShowing = savedInstanceState?.getBoolean("dialogIsShow", false) == true
        printResult = savedInstanceState?.getBoolean("printResult", false) == true
        return activity?.let {
            thisOrient = activity?.requestedOrientation!!
            activity?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED)
            val builder = AlertDialog.Builder(it, R.style.Dialog)
            builder.setView(R.layout.info_dialog)
            builder.create()


        } ?: throw IllegalStateException("Activity cannot be null")


    }

    override fun onStart() {
        super.onStart()

        image = this.requireDialog().findViewById(R.id.image_dialog)
        text = this.requireDialog().findViewById(R.id.text_modal)
        button = this.requireDialog().findViewById(R.id.button)
        progress = this.requireDialog().findViewById(R.id.progressBar)
        frame = this.requireDialog().findViewById(R.id.frame)

        button.setOnClickListener{
            this.closeNow()
        }
        backSetUnclickable()

        if(!printResult){
            image.visibility = View.GONE
            text.visibility = View.VISIBLE
            button.visibility = View.GONE
            progress.visibility = View.VISIBLE

            text.setText(message)
        }
        else{
            image.visibility = View.VISIBLE
            text.visibility = View.VISIBLE
            button.visibility = View.VISIBLE
            progress.visibility = View.GONE

            text.setText(message)
        }

    }

    override fun onResume() {
        super.onResume()
        if(!isShowing){
            closeNow()
        }
    }


    private fun backSetUnclickable(){
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
    }

    fun openNow(){
        if(!isShowing) {
            isShowing = true
            if (!this.isAdded) {
                this.showNow(_fragmentManager, null)
            } else {
                dialog?.show()
            }
        }
    }




    fun closeNow(){
        if (isShowing) {
            isShowing = false
            dialog?.hide()
        }

    }

    fun showLoadingNow(message: String){
        this.message = message
        openNow()
        CoroutineScope(Dispatchers.Main).async {
            this@InfoDialog.whenStarted {
                progress.visibility = View.VISIBLE
                image.visibility = View.GONE
                text.text = message
                text.visibility = View.VISIBLE
                button.visibility = View.GONE
                printResult = false
            }
        }
    }

    fun showResultNow(message: String, isError: Boolean = false){
        this.message = message
        openNow()
        CoroutineScope(Dispatchers.Main).async {
            this@InfoDialog.whenStarted {
                if(isError){
                    image.setImageResource(errorImg)
                    button.setText("Попробовать позже")
                }
                else{
                    image.setImageResource(okImg)
                    button.setText("Хорошо")
                }
                progress.visibility = View.GONE
                image.visibility = View.VISIBLE
                text.text = message
                text.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
                printResult = true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("dialogIsShow", this.isShowing)
        outState.putBoolean("printResult", this.printResult)
        super.onSaveInstanceState(outState)
    }
}