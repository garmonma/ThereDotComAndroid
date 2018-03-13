package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.location.Location
import android.nni.com.theredotcomandroid.entities.Hotel
import android.nni.com.theredotcomandroid.services.callbacks.JSONArrayServerCallback
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

/**
 * Created by Marcus Garmon on 3/12/2018.
 */
class HotelService(context: Activity) {
    private val TAG = "Hotel Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)


    fun getAirBnBOptions(): ArrayList<Hotel> {
        var airBnbList = ArrayList<Hotel>()

        var airBnb1 = Hotel()
        var airBnb2 = Hotel()
        var airBnb3 = Hotel()
        var airBnb4 = Hotel()

        airBnbList.add(airBnb1)
        airBnbList.add(airBnb2)
        airBnbList.add(airBnb3)
        airBnbList.add(airBnb4)

        return airBnbList
    }

    fun getHotelSuggestions(hotelType: String, location: Location, callback: JSONArrayServerCallback){
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk." +
                "com/api/hotelSuggestions/" + hotelType

        val getSuggestions = JsonArrayRequest(
                url,
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error)}
        )

        queue.add(getSuggestions)
    }
}