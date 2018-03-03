package android.nni.com.theredotcomandroid.services

import android.app.Activity
import android.nni.com.theredotcomandroid.dtos.LoginDto
import android.nni.com.theredotcomandroid.dtos.RegisterDTO
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


    fun registerAccount(registerDTO: RegisterDTO?, callback: JSONObjectServerCallback){
        val url = "http://192.168.0.2:8080/api/register"

        var newAccount = Account()
        val user = User()
        user.convertRegisterDro(registerDTO!!)

        newAccount.user = user
        newAccount.email = registerDTO.email
        newAccount.setName(registerDTO)

        Log.i(TAG, newAccount.toString())
        System.out.println(TAG + newAccount)

        val register = JsonObjectRequest(Request.Method.POST, url, newAccount.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        queue.add(register)
    }

    fun login(loginDto: LoginDto?, callback: JSONObjectServerCallback){
        val url = "http://192.168.0.2:8080/api/login"

        val user = User()
        user.convertLoginDto(loginDto!!)

        System.out.println(user)

        val register = JsonObjectRequest(Request.Method.POST, url, user.toJSON(),
                Response.Listener { response ->
                    Log.i(TAG, "Response : " + response)
                    callback.onSuccess(response)
                },
                Response.ErrorListener { error -> Log.i(TAG, "Error : " + error) })

        queue.add(register)
    }
}