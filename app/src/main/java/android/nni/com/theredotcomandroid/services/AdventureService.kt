package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.entities.Adventure
import android.nni.com.theredotcomandroid.beans.AdventureBean
import android.nni.com.theredotcomandroid.services.callbacks.JSONArrayServerCallback
import android.nni.com.theredotcomandroid.services.callbacks.JSONObjectServerCallback
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class AdventureService(context: Activity)  {
    private val TAG = "Profile Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    fun writeAdventureToFile(adventure: AdventureBean?){
        TODO("Not Implemented")
    }

    fun createAdventure(adventure: AdventureBean?, callback: JSONObjectServerCallback){
        val url = "http://192.168.0.2:8080/api/adventure"

        val adv = Adventure()
        adv.convertAdventureBean(adventure!!)

        System.out.println(adv)

        // Request a string response from the provided URL.
        val createAdventure = JsonObjectRequest(Request.Method.POST, url, adv.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        // Add the request to the RequestQueue.
        queue.add(createAdventure)
    }

    fun getAdventures(accountId: Long?, callback: JSONArrayServerCallback){
        val url = "http://192.168.0.2:8080/api/adventures?accountId=" + accountId

        // Request a string response from the provided URL.
        val getAdventures = JsonArrayRequest( url,
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        // Add the request to the RequestQueue.
        queue.add(getAdventures)
    }
}
