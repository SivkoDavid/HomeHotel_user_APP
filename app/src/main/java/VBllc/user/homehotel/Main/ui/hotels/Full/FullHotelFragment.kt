package VBllc.user.homehotel.Main.ui.hotels.Full

import VBllc.user.homehotel.API.DataResponse.HotelResponse
import VBllc.user.homehotel.API.DataResponse.HotelsResponse
import VBllc.user.homehotel.AdditionalComponents.DialogWindows.ReviewDialog.ReviewEditDialog
import VBllc.user.homehotel.AdditionalComponents.DialogWindows.ReviewDialog.ReviewEditListener
import VBllc.user.homehotel.DataLayer.Preferences.UserInfoPreference
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Tools.DateFormatter
import VBllc.user.homehotel.Views.FullHotelView
import android.view.View.inflate
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.whenStarted
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class FullHotelFragment : Fragment(), FullHotelView {

    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var address: TextView
    private lateinit var orgName: TextView
    private lateinit var ratingHotel: TextView
    private lateinit var reviewLayout: LinearLayout
    private lateinit var editReviewButton: Button
    private lateinit var editReviewDialog: ReviewEditDialog
    private lateinit var ratingBarFullHotel: RatingBar

    private val presenter = FullHotelPresenter(this)

    private fun initViews(root: View){
        name = root.findViewById(R.id.hotelName)
        phone = root.findViewById(R.id.hotelPhone)
        address = root.findViewById(R.id.hotelAddress)
        orgName = root.findViewById(R.id.orgName)
        ratingHotel = root.findViewById(R.id.ratingHotel)
        reviewLayout = root.findViewById(R.id.reviewLayout)
        editReviewButton = root.findViewById(R.id.editReviewButton)
        ratingBarFullHotel = root.findViewById(R.id.ratingBarFullHotel)

        editReviewDialog = ReviewEditDialog(requireActivity().supportFragmentManager)
        editReviewDialog.listener = object : ReviewEditListener{
            override fun reviewMade(rating: Int, review: String?) {
                presenter.reviewMade(rating, review)
            }
        }

        editReviewButton.setOnClickListener { editReviewDialog.showEditorNow() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_full_hotel, container, false)
        initViews(root)
        return root
    }

    fun printInfo(hotel: HotelResponse.HotelData){
        name.text = hotel.hotel.name
        address.text = hotel.hotel.addres
        phone.text = hotel.hotel.main_phone
        orgName.text = hotel.organisation.name
        fillReviews(hotel.reviews)
    }

    fun newInstance(hotel: HotelResponse.HotelData): FullHotelFragment{
        presenter.hotel = hotel
        return this
    }

    fun fillReviews(reviews: List<HotelResponse.ReviewsData>){
        reviewLayout.removeAllViewsInLayout()
        ratingBarFullHotel.rating = 0f
        var ratingSum = 0
        reviews.forEach{
            val card = layoutInflater.inflate(R.layout.review_card, null)
            val userName = card.findViewById<TextView>(R.id.userName)
            val textReview = card.findViewById<TextView>(R.id.textReview)
            val dateTime = card.findViewById<TextView>(R.id.dateTime)
            val raingReview = card.findViewById<RatingBar>(R.id.raingReviewInCard)
            userName.text = it.user_name
            if(it.text?.isEmpty()?:true)
                textReview.visibility = View.GONE
            textReview.text = it.text
            raingReview.rating = it.rating.toFloat()
            val date = DateFormatter.formattedDateTime(it.created_at?:"")
            dateTime.text = date
            ratingSum += it.rating
            reviewLayout.addView(card)
        }

        if(reviews.count() > 0) {
            val rat = (((ratingSum.toFloat() / reviews.count()) * 100).roundToInt().toFloat() / 100)
            ratingBarFullHotel.rating = rat
            ratingHotel.text = "Рейтинг:" + rat.toString()
        }
        else
            ratingHotel.text = "Нет отзывов"
    }

    override fun showLoadingSendReview() {
        CoroutineScope(Dispatchers.Main).launch {
            editReviewDialog.showLoadingNow("Отправка отзыва")
        }
    }

    override fun showErrorSendReview(errorMessage: String) {
        CoroutineScope(Dispatchers.Main).launch {
            editReviewDialog.showResultNow(errorMessage, true)
        }
    }

    override fun showAllINfo(hotel: HotelResponse.HotelData?) {
        CoroutineScope(Dispatchers.Main).launch {
            if(hotel!=null)
                printInfo(hotel)
            editReviewDialog.closeNow()
        }
    }

    override fun showButtonSend(isShow: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            this@FullHotelFragment.whenStarted {
                if (isShow)
                    editReviewButton.visibility = View.VISIBLE
                else
                    editReviewButton.visibility = View.GONE
            }
        }
    }

    override fun showError(errorMessage: String, errorCode: Int) {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }

    override fun showLoading() {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }

    override fun showNoNetwork() {
        CoroutineScope(Dispatchers.Main).launch {

        }
    }
}