package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.entities.Adventure
import android.nni.com.theredotcomandroid.beans.AdventureBean
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson


class CalculatorPersistService(context: Activity)  {
    private val TAG = "Profile Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    private var gson = Gson()


    fun writeAdventureToFile(adventure: AdventureBean?){
        TODO("Not Implemented")
    }

    fun writeAdventureToDatabase(adventure: AdventureBean?) {
        TODO("Not Implemented")
    }

    fun createAdventure(adventure: AdventureBean?, callback: ServerCallback){
        val url = "http://192.168.0.2:8080/api/adventure"

        var adv = Adventure()
        adv.convertAdventureBean(adventure!!)

        System.out.println(adv)

        // Request a string response from the provided URL.
        val createProfile = JsonObjectRequest(Request.Method.POST, url, adv.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        // Add the request to the RequestQueue.
        queue.add(createProfile)
    }
}
