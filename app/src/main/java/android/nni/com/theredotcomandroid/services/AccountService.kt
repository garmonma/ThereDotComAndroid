package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.dtos.LoginDto
import android.nni.com.theredotcomandroid.dtos.RegisterDto
import android.nni.com.theredotcomandroid.entities.Account
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
    private val TAG = "Account Service"

    private var queue: RequestQueue = Volley.newRequestQueue(context)


    fun registerAccount(registerDto: RegisterDto?, callback: JSONObjectServerCallback){
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk.com/api/register"

        var newAccount = Account()
        val user = User()
        user.convertRegisterDro(registerDto!!)

        newAccount.user = user
        newAccount.email = registerDto.email
        newAccount.setName(registerDto)

        Log.i(TAG, newAccount.toString())

        val register = JsonObjectRequest(Request.Method.POST, url, newAccount.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        queue.add(register)
    }

    fun login(loginDto: LoginDto?, callback: JSONObjectServerCallback){
        val url = "http://taccitservice-env-dev.us-east-2.elasticbeanstalk.com/api/login"

        val user = User()
        user.convertLoginDto(loginDto!!)

        Log.i(TAG, user.toString())

        val register = JsonObjectRequest(Request.Method.POST, url, user.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        queue.add(register)
    }
}