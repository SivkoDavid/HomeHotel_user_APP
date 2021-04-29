package VBllc.user.homehotel.AdditionalComponents.DialogWindows.ReviewDialog

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
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ReviewEditDialog(
    val _fragmentManager: FragmentManager,
    var message: String = "Идет обработка запроса"
) : DialogFragment() {

    var listener: ReviewEditListener? = null

    var thisOrient:Int = 0;
    val errorImg  = R.drawable.ic_alert
    val okImg = R.drawable.ic_success

    var printResult = false

    var isShowing = false
    lateinit var image: ImageView
    lateinit var text: TextView
    lateinit var button: Button
    lateinit var progress: ProgressBar
    lateinit var rating: RatingBar
    lateinit var review: TextInputLayout
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
            builder.setView(R.layout.review_edit_dialog)
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
        rating = this.requireDialog().findViewById(R.id.ratingBar)
        review = this.requireDialog().findViewById(R.id.rviewInput)



    }


    private fun backSetUnclickable(){
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
    }

    private fun backSetClickable(){
        dialog!!.setCanceledOnTouchOutside(true)
        dialog!!.setCancelable(true)
    }

    fun openNow(){
        isShowing = true
        if(!isAdded){
            this.show(_fragmentManager, "")
        }
        else
            dialog?.show()
    }

    fun sendReview(rating: Int, review: String){
        var rev: String? = null
        if(review.isNotEmpty())
            rev = review

        listener?.reviewMade(rating, rev)
    }

    fun closeNow(){
        if (dialog?.isShowing?:false) {
            isShowing = false
            dialog?.dismiss()
            activity?.setRequestedOrientation(thisOrient)
        }

    }

    fun showEditorNow(){
        openNow()
        CoroutineScope(Dispatchers.Main).async {
            this@ReviewEditDialog.whenStarted {
                backSetClickable()
                newInstance()
                progress.visibility = View.GONE
                image.visibility = View.GONE
                text.text = "Оставте свой отзыв"
                text.visibility = View.VISIBLE
                rating.visibility = View.VISIBLE
                review.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
                button.text = "Отправить"
                button.setOnClickListener{
                    this@ReviewEditDialog.sendReview(rating.rating.toInt(), review.editText?.text.toString())
                }
            }
        }
    }

    fun showLoadingNow(message: String){
        this.message = message
        openNow()
        CoroutineScope(Dispatchers.Main).async {
            this@ReviewEditDialog.whenStarted {
                backSetUnclickable()
                progress.visibility = View.VISIBLE
                image.visibility = View.GONE
                text.text = message
                text.visibility = View.VISIBLE
                button.visibility = View.GONE
                printResult = false
                rating.visibility = View.GONE
                review.visibility = View.GONE
            }
        }
    }

    fun showResultNow(message: String, isError: Boolean = false){
        this.message = message
        openNow()
        CoroutineScope(Dispatchers.Main).async {
            this@ReviewEditDialog.whenStarted {
                backSetClickable()
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
                button.text = "Хорошо"
                printResult = true
                rating.visibility = View.GONE
                review.visibility = View.GONE
                button.setOnClickListener{
                    this@ReviewEditDialog.closeNow()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("dialogIsShow", this.isShowing)
        outState.putBoolean("printResult", this.printResult)
        super.onSaveInstanceState(outState)
    }

    fun newInstance(){
        rating.rating = 0f
        review.editText?.setText("")
    }

}