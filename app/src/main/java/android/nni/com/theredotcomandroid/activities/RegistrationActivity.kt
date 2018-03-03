package android.nni.com.theredotcomandroid.activities

import android.accounts.Account
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.nni.com.theredotcomandroid.R
import android.nni.com.theredotcomandroid.dtos.LoginDto
import android.nni.com.theredotcomandroid.dtos.RegisterDTO
import android.nni.com.theredotcomandroid.services.AccountService
import android.nni.com.theredotcomandroid.services.callbacks.JSONArrayServerCallback
import android.nni.com.theredotcomandroid.services.callbacks.JSONObjectServerCallback
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText

import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.content_registration.*
import org.json.JSONArray
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity(), OnClickListener {


    private var nameText: EditText? = null
    private var usernameText: EditText? = null
    private var emailText: EditText? = null
    private var passwordText: EditText? = null

    private var signUpButton: Button? = null
    private var loginButton: Button? = null

    private var accountService : AccountService? = null
    private var registerDto : RegisterDTO? = null
    private var loginDto: LoginDto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        setSupportActionBar(toolbar)

        nameText = findViewById(R.id.registerNameText)
        usernameText = findViewById(R.id.registerUsernameText)
        emailText = findViewById(R.id.registerEmailText)
        passwordText = findViewById(R.id.registerPasswordText)

        signUpButton = findViewById(R.id.registerSignUpButton)
        loginButton = findViewById(R.id.registerLoginButton)


        signUpButton?.setOnClickListener(this)
        loginButton?.setOnClickListener(this)

        accountService = AccountService(this)

    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.registerSignUpButton){
            registerNewUser()
        }

        if(v?.id == R.id.registerLoginButton){
            loginUser()
        }
    }

    private fun registerNewUser(){
        registerDto = RegisterDTO()
        registerDto?.name = nameText?.text.toString()
        registerDto?.username = usernameText?.text.toString()
        registerDto?.email = emailText?.text.toString()
        registerDto?.password = passwordText?.text.toString()

        accountService?.registerAccount(registerDto, object : JSONObjectServerCallback {
            override fun onSuccess(result: JSONObject) {
                loginUser()
            }
        })
    }

    private fun loginUser(){
        loginDto = LoginDto()
        // username.text could be the email or username
        loginDto?.username = usernameText?.text.toString()
        loginDto?.password = passwordText?.text.toString()

        accountService?.login(loginDto, object : JSONObjectServerCallback {
            override fun onSuccess(result: JSONObject) {
                // Grab account id and change intent to Calculator Activity
            }
        })
    }

}