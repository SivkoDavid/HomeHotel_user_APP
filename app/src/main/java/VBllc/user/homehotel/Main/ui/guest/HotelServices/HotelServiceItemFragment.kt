package VBllc.user.homehotel.Main.ui.guest.HotelServices

import VBllc.user.homehotel.API.DataResponse.HotelServicesResponse.HotelServiceData
import VBllc.user.homehotel.API.DataResponse.SettleResponse
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import VBllc.user.homehotel.R

/**
 * A fragment representing a list of Items.
 */
class HotelServiceItemFragment : Fragment() {

    private var recucler: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    var servicesList: MutableList<HotelServiceData> = mutableListOf()
        set(value) {
            field?.clear()
            field?.addAll(value?: listOf())
            recucler?.adapter?.notifyDataSetChanged()
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_h_service_item_list, container, false)

        servicesList = testListService.toMutableList()
        // Set the adapter
        if (view is RecyclerView) {
            recucler = view
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = HotelServiceItemRecyclerViewAdapter(servicesList)
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        servicesList = testListService.toMutableList()
    }

    companion object {
        fun newInstance(data: SettleResponse.SettleData): Fragment {
            val args = Bundle()

            val fragment = HotelServiceItemFragment()

            return fragment
        }

    }

}

val testListService = listOf<HotelServiceData>(
        HotelServiceData(
                1,
                "Услуга 1",
                2340.0,
                "руб/час",
                "Описание услуги в несколько строк. Описание услуги в несколько строк. Описание услуги в несколько строк. Описание услуги в несколько строк." +
                        "Описание услуги в несколько строк. Описание услуги в несколько строк. Описание услуги в несколько строк. Описание услуги в несколько строк.",
                "Развлечения",
                "https://im0-tub-ru.yandex.net/i?id=5b7bd76e3b67e515f83ef5da975e6b4c&n=13"
        ),
        HotelServiceData(
                1,
                "Услуга 1",
                2340.0,
                "руб/час",
                "Описание услуги в несколько строк. Описание услуги в несколько строк.",
                "Развлечения",
                "https://im0-tub-ru.yandex.net/i?id=5b7bd76e3b67e515f83ef5da975e6b4c&n=13"
        ),
        HotelServiceData(
                1,
                "Услуга 1",
                2340.0,
                "руб/час",
                "Описание услуги в несколько строк. Описание услуги в несколько строк.",
                "Развлечения",
                "https://im0-tub-ru.yandex.net/i?id=5b7bd76e3b67e515f83ef5da975e6b4c&n=13"
        ),
        HotelServiceData(
                1,
                "Услуга 1",
                2340.0,
                "руб/час",
                "Описание услуги в несколько строк. Описание услуги в несколько строк.",
                "Развлечения",
                "https://im0-tub-ru.yandex.net/i?id=5b7bd76e3b67e515f83ef5da975e6b4c&n=13"
        ),
        HotelServiceData(
                1,
                "Услуга 1",
                2340.0,
                "руб/час",
                "Описание услуги в несколько строк. Описание услуги в несколько строк.",
                "Развлечения",
                "https://im0-tub-ru.yandex.net/i?id=5b7bd76e3b67e515f83ef5da975e6b4c&n=13"
        ),
        HotelServiceData(
                1,
                "Услуга 1",
                2340.0,
                "руб/час",
                "Описание услуги в несколько строк. Описание услуги в несколько строк.",
                "Развлечения",
                "https://im0-tub-ru.yandex.net/i?id=5b7bd76e3b67e515f83ef5da975e6b4c&n=13"
        )
)