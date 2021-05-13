package VBllc.user.homehotel.Tools

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {


    companion object{
        /**
         * yyyy-MM-dd HH:mm:ss
         */
        val DT_FORMAT_SIMPLE = "yyyy-MM-dd HH:mm:ss"
        val DT_FORMAT_SIMPLE_T = "yyyy-MM-dd'T'HH:mm:ss"

        /**
         * yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ
         *   2021-02-16T14:35:24.000000Z
         */
        val DT_FORMAT_FULL= "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"



        /**
         * dd.MM.yyyy
         */
        val D_FORMAT_1 = "dd.MM.yyyy"

        /**
         * yyyy-MM-dd HH:mm:ss ±hh
         */
        val DTFORMAT_WITH_TIMEZONE = "yyyy-MM-dd HH:mm:ss+HH"


        fun formattedDateTime(date:String, format:String = DT_FORMAT_SIMPLE):String{
            var res = date
            if(res.count() > 19)
                res = res.substring(0, 19)
            try {
                val parser = SimpleDateFormat(format)
                val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")

                val activatedDate = parser.parse(res)
                val formattedDate = formatter.format(activatedDate)
                return formattedDate
            }
            catch (e : ParseException){
                return res
            }

        }

        fun toDate(strDate:String, format:String = DT_FORMAT_SIMPLE): Date {
            try {
                val parser = SimpleDateFormat(format)
                val formattedDate = parser.parse(strDate)
                return formattedDate
            }
            catch (e : ParseException){
                return Date()
            }
        }

        fun getOnlyDate(dateTime: Date):Date{
            dateTime.time -= dateTime.time%86400000
            return dateTime
        }
    }
}