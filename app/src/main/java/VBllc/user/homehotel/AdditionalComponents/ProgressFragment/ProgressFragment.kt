package VBllc.user.homehotel.AdditionalComponents.ProgressFragment

import VBllc.user.homehotel.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ProgressFragment(val _parentMAnager: FragmentManager) : Fragment() {

    var listener: ProgressFragmentListener? = null

    companion object{
        val ERROR_IMAGE = R.drawable.ic_alert
        val OK_IMAGE = R.drawable.ic_success
    }

    lateinit var imageStatus: ImageView
    lateinit var textStatus: TextView
    lateinit var buttonReload: Button
    lateinit var progressBar: ProgressBar

    var isLoading = true

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_progress, container, false)
        imageStatus = view.findViewById(R.id.imageStatusProgFrag)
        textStatus = view.findViewById(R.id.textStatusProgFrag)
        buttonReload = view.findViewById(R.id.buttonReloadProgFrag)
        progressBar = view.findViewById(R.id.progressFragProgress)
        buttonReload.setOnClickListener { listener?.buttonReloadClick() }
        return view
    }


    fun showLoading(){
        open()
        isLoading = true
        CoroutineScope(Dispatchers.Main).async {
            whenStarted {
                imageStatus.visibility = View.INVISIBLE
                textStatus.visibility = View.INVISIBLE
                buttonReload.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        }
    }

    private var last_image = ERROR_IMAGE
    private var last_text = ""

    fun showStatus(message: String, imageRes: Int = ERROR_IMAGE){
        open()
        isLoading = false
        CoroutineScope(Dispatchers.Main).async {
            whenStarted {
                imageStatus.visibility = View.VISIBLE
                textStatus.visibility = View.VISIBLE
                buttonReload.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE

                if (imageRes != last_image)
                    imageStatus.setImageResource(imageRes)
                if (last_text != message)
                    textStatus.setText(message)
            }
        }
    }

    fun open(){
        _parentMAnager.beginTransaction().show(this).commit()
    }

    fun hide(){
        _parentMAnager.beginTransaction().hide(this).commit()
    }
}