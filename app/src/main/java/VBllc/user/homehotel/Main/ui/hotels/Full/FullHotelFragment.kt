package VBllc.user.homehotel.Main.ui.hotels.Full

import VBllc.user.homehotel.API.DataResponse.HotelResponse
import VBllc.user.homehotel.API.DataResponse.HotelsResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R
import VBllc.user.homehotel.Tools.DateFormatter
import android.view.View.inflate
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.math.roundToInt

class FullHotelFragment : Fragment() {

    private var data: HotelsResponse.HotelData? = null

    private lateinit var name: TextView
    private lateinit var phone: TextView
    private lateinit var address: TextView
    private lateinit var orgName: TextView
    private lateinit var ratingHotel: TextView
    private lateinit var reviewLayout: LinearLayout

    private fun initViews(root: View){
        name = root.findViewById(R.id.hotelName)
        phone = root.findViewById(R.id.hotelPhone)
        address = root.findViewById(R.id.hotelAddress)
        orgName = root.findViewById(R.id.orgName)
        ratingHotel = root.findViewById(R.id.ratingHotel)
        reviewLayout = root.findViewById(R.id.reviewLayout)
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
        printInfo(hotel)
        return this
    }

    fun fillReviews(reviews: List<HotelResponse.ReviewsData>){
        reviewLayout.removeAllViewsInLayout()
        var ratingSum = 0
        reviews.forEach{
            val card = layoutInflater.inflate(R.layout.review_card, null)
            val userName = card.findViewById<TextView>(R.id.userName)
            val textReview = card.findViewById<TextView>(R.id.textReview)
            val dateTime = card.findViewById<TextView>(R.id.dateTime)
            val raingReview = card.findViewById<TextView>(R.id.raingReview)
            userName.text = it.user_name
            if(it.text?.isEmpty()?:true)
                textReview.visibility = View.GONE
            textReview.text = it.text
            raingReview.text = it.rating.toString()
            val date = DateFormatter.formattedDateTime(it.created_at?:"")
            dateTime.text = date
            ratingSum += it.rating
            reviewLayout.addView(card)
        }

        if(reviews.count() > 0)
            ratingHotel.text = "Рейтинг:" + (((ratingSum.toFloat()/reviews.count())*100).roundToInt().toFloat()/100).toString()
        else
            ratingHotel.text = "Нет отзывов"
    }
}