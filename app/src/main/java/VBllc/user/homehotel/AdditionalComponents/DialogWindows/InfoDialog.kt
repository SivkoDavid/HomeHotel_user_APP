package VBllc.user.homehotel.AdditionalComponents.DialogWindows

import VBllc.user.homehotel.R
import android.animation.Animator
import android.animation.ValueAnimator
import android.app.AlertDialog
import android.app.Dialog
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.forEachIndexed
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class InfoDialog(
        val _fragmentManager: FragmentManager,
        val _teg: String = "dialog_1",
        var message: String = "Идет обработка запроса"
) : DialogFragment() {

    var thisOrient:Int = 0;
    val errorImg  = R.drawable.ic_success
    val okImg = R.drawable.ic_alert

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

        isShowing = true
        image = this.requireDialog().findViewById(R.id.image_dialog)
        text = this.requireDialog().findViewById(R.id.text_modal)
        button = this.requireDialog().findViewById(R.id.button)
        progress = this.requireDialog().findViewById(R.id.progressBar)
        frame = this.requireDialog().findViewById(R.id.frame)

        button.setOnClickListener{
            this.animateClose()
        }
        animateOpen(frame!!)
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



    private fun backSetUnclickable(){
        dialog!!.setCanceledOnTouchOutside(false)
        dialog!!.setCancelable(false)
    }

    private fun backSetClickable(){
        dialog!!.setCanceledOnTouchOutside(true)
        dialog!!.setCancelable(true)
    }

    fun showResult(message: String,  isError: Boolean = false){
        this.message = message
        if(frame != null)
            animatShowResult(frame!!, isError)
    }


    var lastStatusResponse = false

    private fun animatShowResult(view: LinearLayout, isError: Boolean = false){
        lastStatusResponse = !isError
        printResult = true
        val animator = ValueAnimator.ofFloat(1f, 0f)
        val aliphaAnimtor = ValueAnimator.ofFloat(1f, 0f)
        val aliphaAnimtor2 = ValueAnimator.ofFloat(0f, 1f)
        val animator2 = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 700
        animator.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view.scaleY = y
            if(y<0.1 && view.visibility == View.VISIBLE)
                view.visibility = View.INVISIBLE
        }
        animator.addListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {   }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                animator2.start()
                view.visibility
                image.visibility = View.INVISIBLE
                text.visibility = View.INVISIBLE
                button.visibility = View.INVISIBLE
                progress.visibility = View.GONE
                text.setText(message)
            }
        })
        animator.duration = 200
        animator.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view.forEachIndexed { index, item ->
                item.alpha = y
            }
        }
        animator2.duration = 700
        animator2.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view.scaleY = y
            if(y > 0.9 && !aliphaAnimtor2.isRunning)
                aliphaAnimtor2.start()
        }
        animator2.addListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) { view.visibility = View.VISIBLE  }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                if(isError){
                    image.setImageResource(errorImg)
                    button.setText("Попробовать позже")
                }
                else{
                    image.setImageResource(okImg)
                    button.setText("Хорошо")
                }
                image.visibility = View.VISIBLE
                text.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
            }
        })
        animator2.duration = 200
        animator2.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view.forEachIndexed { index, item ->
                item.alpha = y
            }
        }
        animator2.start()

        aliphaAnimtor.start()
        animator.start()
    }

    private fun animateOpen(view: LinearLayout){
        val aliphaAnimtor = ValueAnimator.ofFloat(0f, 1f)

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view.scaleY = y
            if (y > 0.9 && !aliphaAnimtor.isRunning)
                aliphaAnimtor.start()
        }

        animator.duration = 200
        animator.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view.forEachIndexed { index, item ->
                item.alpha = y
            }
        }
        animator.start()
    }

    fun animateClose(view: LinearLayout? = frame){
        val animator = ValueAnimator.ofFloat(1f, 0f)
        animator.duration = 700
        animator.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view?.scaleY = y
            if(y < 0.1 && view?.visibility == View.VISIBLE)
                view.visibility = View.INVISIBLE
        }
        animator.addListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) { view?.visibility = View.VISIBLE  }
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                sendResult(lastStatusResponse)
                this@InfoDialog.close()
            }
        })
        animator.duration = 200
        animator.addUpdateListener { animation ->
            val y = (animation.animatedValue as Float)
            view?.forEachIndexed { index, item ->
                item.alpha = y
            }
        }
        animator.start()
    }


    fun close(){
        if (dialog?.isShowing?:false) {
            isShowing = false
            dialog!!.cancel()
            activity?.setRequestedOrientation(thisOrient)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("dialogIsShow", this.isShowing)
        outState.putBoolean("printResult", this.printResult)
        super.onSaveInstanceState(outState)
    }

    ///----------Callback-----------

    var listener: InfoDialogCallback? = null

    private fun sendResult(status: Boolean){
        listener?.onDialogClose(status)
    }
}