package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.entities.Adventure
import android.nni.com.theredotcomandroid.dtos.AdventureDto
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
    private val TAG = "Adventure Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    fun writeAdventureToFile(adventure: AdventureDto?){
        TODO("Not Implemented")
    }

    fun createAdventure(adventure: AdventureDto?, callback: JSONObjectServerCallback){
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk.com/api/adventure"

        val adv = Adventure()
        adv.convertAdventureBean(adventure!!)

        Log.i(TAG, adv.toString())

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
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk.com/api/adventures/" + accountId

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
