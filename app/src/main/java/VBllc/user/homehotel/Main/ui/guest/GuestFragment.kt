package VBllc.user.homehotel.Main.ui.guest

import VBllc.user.homehotel.API.DataResponse.SettleResponse
import VBllc.user.homehotel.AdditionalComponents.DialogWindows.InfoDialog
import VBllc.user.homehotel.Chat.ChatActivity
import VBllc.user.homehotel.Main.ui.guest.Cleaning.OrderCleaningFragment
import VBllc.user.homehotel.Main.ui.guest.Gastronomy.GastronomyFragment
import VBllc.user.homehotel.Main.ui.guest.HotelServices.HotelServiceItemFragment
import VBllc.user.homehotel.Main.ui.guest.Hygiene.HygieneFragment
import VBllc.user.homehotel.Main.ui.guest.PartnerServices.PartnerServiceFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Tools.DateFormatter
import VBllc.user.homehotel.Views.GuestView
import android.content.Intent
import android.widget.Button
import androidx.lifecycle.whenStarted
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GuestFragment : Fragment(), GuestView {

    private lateinit var settlementLayout: View
    private lateinit var notSettlementLayout: View
    private lateinit var codeInput: TextInputLayout
    private lateinit var sendCodeBtn: Button
    private lateinit var infoDialog: InfoDialog
    private lateinit var hotelNameView: TextView
    private lateinit var addressView: TextView
    private lateinit var numView: TextView
    private lateinit var startDateView: TextView
    private lateinit var startTimeView: TextView
    private lateinit var endDateView: TextView
    private lateinit var endTimeView: TextView
    private lateinit var foodMenu: Button
    private lateinit var hygieneMenu: Button
    private lateinit var cleaningMenu: Button
    private lateinit var otherServicesMenu: Button
    private lateinit var partServicesMenu: Button
    private lateinit var messagerMenu: Button
    private lateinit var outButton: Button

    private lateinit var presenter: GuestPresenter

    fun initViews(root: View){
        settlementLayout = root.findViewById(R.id.guest_layout)
        notSettlementLayout = root.findViewById(R.id.not_guest_layout)
        codeInput = root.findViewById(R.id.codeInput)
        sendCodeBtn = root.findViewById(R.id.sendCodeBtn)
        hotelNameView = root.findViewById(R.id.hotelName)
        addressView = root.findViewById(R.id.addressFilial)
        numView = root.findViewById(R.id.numberNum)
        startDateView = root.findViewById(R.id.dateStart)
        startTimeView = root.findViewById(R.id.timeStart)
        endDateView = root.findViewById(R.id.dateEnd)
        endTimeView = root.findViewById(R.id.timeEnd)
        foodMenu = root.findViewById(R.id.foodMenu)
        hygieneMenu = root.findViewById(R.id.hygieneMenu)
        cleaningMenu = root.findViewById(R.id.cleaningMenu)
        otherServicesMenu = root.findViewById(R.id.otherServicesMenu)
        partServicesMenu = root.findViewById(R.id.partServicesMenu)
        messagerMenu = root.findViewById(R.id.messagerMenu)
        outButton = root.findViewById(R.id.outButton)

        sendCodeBtn.setOnClickListener { sendCodeBtnClick() }
        cleaningMenu.setOnClickListener { presenter.goToCleaningMenu() }
        otherServicesMenu.setOnClickListener { presenter.goToHotelServicesMenu() }
        partServicesMenu.setOnClickListener { presenter.goToPartnersServicesMenu() }
        hygieneMenu.setOnClickListener { presenter.goToHygieneMenu() }
        foodMenu.setOnClickListener { presenter.goToGastronomyMenu() }
        messagerMenu.setOnClickListener { presenter.goToChatMenu() }
        outButton.setOnClickListener { presenter.outOfTheSettle() }

    }


    private fun initFragments(){

    }

    private fun printSettleInfo(data: SettleResponse.SettleData){
        hotelNameView.text = data.hotel.name
        addressView.text = data.filial.addres
        numView.text = data.apartament.name

        val dStart = DateFormatter.formattedDateTime(data.settlement_start)
        val dEnd = DateFormatter.formattedDateTime(data.settlement_end)

        val dateS = dStart.substringBefore(' ')
        val timeS = dStart.substringAfter(' ')

        val dateE = dEnd.substringBefore(' ')
        val timeE = dEnd.substringAfter(' ')

        startDateView.text = dateS
        startTimeView.text = timeS
        endDateView.text = dateE
        endTimeView.text = timeE
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_guest, container, false)
        initViews(root)
        initFragments()
        presenter = GuestPresenter(this)
        infoDialog = InfoDialog(requireActivity().supportFragmentManager, "guest123")
        return root
    }

    override fun onStart() {
        super.onStart()
    }

    private fun sendCodeBtnClick(){
        if((codeInput.editText?.text?.length?:0) > 0){
            presenter.sendSetteleCode(codeInput.editText?.text.toString())
        }
    }

    private fun openGuestMode(){
        settlementLayout.visibility = View.VISIBLE
        notSettlementLayout.visibility = View.GONE
    }

    private fun closeGuestMode(){
        settlementLayout.visibility = View.GONE
        notSettlementLayout.visibility = View.VISIBLE
    }

    override fun showSettlement(data: SettleResponse.SettleData, showDialog: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                if(showDialog)
                    infoDialog.showResultNow(message = "Успешно", isError = false)
                else
                    infoDialog.closeNow()
                openGuestMode()
                printSettleInfo(data)
            }
        }
    }

    override fun showNoGuestMode() {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                closeGuestMode()
            }
        }
    }

    override fun infoDialodHide() {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                infoDialog.closeNow()
            }
        }
    }

    override fun showCleaningFragment(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                val cleaningFragment = OrderCleaningFragment.newInstance(data)
                openFragment(cleaningFragment)
            }
        }
    }


    override fun showHotelServicesFragment(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                val serFragment = HotelServiceItemFragment.newInstance(data)
                openFragment(serFragment)
            }
        }
    }

    override fun showPartnersServicesFragment(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                val serFragment = PartnerServiceFragment.newInstance(data)
                openFragment(serFragment)
            }
        }
    }

    override fun showHygieneFragment(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                val fragment = HygieneFragment.newInstance(data)
                openFragment(fragment)
            }
        }
    }

    override fun showGastronomyFragment(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                val fragment = GastronomyFragment.newInstance(data)
                openFragment(fragment)
            }
        }
    }

    override fun showChatFragment(data: SettleResponse.SettleData) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                val intent = Intent(requireContext(), ChatActivity::class.java)
                requireContext().startActivity(intent)
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                infoDialog.showResultNow(errorMessage, true)
            }
        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                infoDialog.showLoadingNow("Проверка кода")
            }
        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {
            this@GuestFragment.whenStarted {
                infoDialog.showResultNow("Нет подключения к интернету", true)
            }
        }
    }

    private fun openFragment(mFragment: Fragment){
        childFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, mFragment).show(mFragment)
            .addToBackStack(null).commit()
    }

}