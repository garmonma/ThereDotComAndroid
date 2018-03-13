package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.entities.Event
import android.nni.com.theredotcomandroid.entities.EventType
import android.nni.com.theredotcomandroid.services.callbacks.JSONArrayServerCallback
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray

/**
 * Created by Marcus Garmon on 3/10/2018.
 */
class EventService(context: Activity) {

    private val TAG = "Event Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    fun getEventTypeList(callback: JSONArrayServerCallback){
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk.com/api/eventTypeList"

        val register = JsonArrayRequest(url,
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        queue.add(register)
    }

    fun getEventTypeList(): ArrayList<EventType> {
        var eventTypeList = ArrayList<EventType>()

        var cruiseEvent = EventType("Cruise")
        var enterRecreat = EventType("Entertainment/Recreational")
        var sightseeing = EventType("Sightseeing")
        var business = EventType("Business")
        var pleasure = EventType("Pleasure")
        var hiking = EventType("Hiking/Outdoors")
        var other = EventType("Other")

        eventTypeList.add(cruiseEvent)
        eventTypeList.add(enterRecreat)
        eventTypeList.add(sightseeing)
        eventTypeList.add(business)
        eventTypeList.add(pleasure)
        eventTypeList.add(hiking)
        eventTypeList.add(other)

        return eventTypeList
    }

    fun getEventSuggestions(eventType: String, callback: JSONArrayServerCallback){
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk." +
                "com/api/eventSuggestions/" + eventType

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