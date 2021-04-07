package VBllc.user.homehotel.Auth.Registration

import VBllc.user.homehotel.Auth.Login.LoginFragmentListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout


class RegistrationFragment : Fragment() {

    private lateinit var nameInput: TextInputLayout
    private lateinit var secondNameInput: TextInputLayout
    private lateinit var emailInput: TextInputLayout
    private lateinit var passInput: TextInputLayout
    private lateinit var secondPassInput: TextInputLayout
    private lateinit var nextButton: Button

    fun initViews(mainView: View){
        nameInput = mainView.findViewById(R.id.nameInput)
        secondNameInput = mainView.findViewById(R.id.secNameInput)
        emailInput = mainView.findViewById(R.id.mailInput)
        passInput = mainView.findViewById(R.id.passInput)
        secondPassInput = mainView.findViewById(R.id.repeatPassInput)
        nextButton = mainView.findViewById(R.id.regNextBtn)

        nextButton.setOnClickListener { nextButtonClick() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)
        initViews(view)
        return view
    }

    private var listener: RegistrationFragmentListener? = null

    fun setListener(listener: RegistrationFragmentListener){
        this.listener = listener
    }

    private fun nextButtonClick(){
        if(fieldIsValid()){
            val name = nameInput.editText?.text?.toString()?:""
            val secName = secondNameInput.editText?.text?.toString()?:""
            val email = emailInput.editText?.text?.toString()?:""
            val pass = passInput.editText?.text?.toString()?:""
            this.listener?.registrationClick(name, secName, email, pass)
        }
    }

    private fun fieldIsValid():Boolean{
        var result = true
        if(nameInput.editText?.text?.isEmpty()?:true){
            result = false
            nameInput.error = "Поле должно быть заполнено"
        }
        else
            nameInput.error = ""


        if(secondNameInput.editText?.text?.isEmpty()?:true){
            result = false
            secondNameInput.error = "Поле должно быть заполнено"
        }
        else
            secondNameInput.error = ""


        val regex = Regex("""^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}${'$'}""")
        if(emailInput.editText?.text?.isEmpty()?:true){
            result = false
            emailInput.error = "Поле должно быть заполнено"
        }
        else if(!regex.matches(emailInput.editText?.text?:"")){
            result = false
            emailInput.error = "Это не Email"
        }
        else
            emailInput.error = ""

        if(passInput.editText?.text?.isEmpty()?:true){
            result = false
            passInput.error = "Поле должно быть заполнено"
        }
        else if((passInput.editText?.text?:"").length < 6 || (passInput.editText?.text?:"").length >40){
            result = false
            passInput.error = "Пароль должен содержать от 6 до 40 символов."
        }
        else
            passInput.error = ""


        if(secondPassInput.editText?.text?.isEmpty()?:true){
            result = false
            secondPassInput.error = "Поле должно быть заполнено"
        }
        else if(secondPassInput.editText?.text?.toString() != passInput.editText?.text?.toString()){
            result = false
            secondPassInput.error = "Пароли не совпадают"
        }
        else
            secondPassInput.error = ""


        return result
    }
}