package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.beans.UserBean
import android.nni.com.theredotcomandroid.entities.User
import android.nni.com.theredotcomandroid.services.callbacks.JSONObjectServerCallback
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

/**
* Created by Marcus Garmon on 2/25/2018.
*/
class AccountService(context: Activity) {
    private val TAG = "Profile Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)

    fun getAccount(user: UserBean?, callback: JSONObjectServerCallback){


    }

    fun registerAccount(userbean: UserBean?, callback: JSONObjectServerCallback){
        val url = "http://192.168.0.2:8080/api/register"

        val newUser = User()
        newUser.convertUserBean(userbean!!)

        System.out.println(newUser)

        // Request a string response from the provided URL.
        val register = JsonObjectRequest(Request.Method.POST, url, newUser.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        // Add the request to the RequestQueue.
        queue.add(register)
    }
}