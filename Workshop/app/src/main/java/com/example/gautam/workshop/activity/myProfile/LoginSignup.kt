package com.example.gautam.workshop.activity.myProfile

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.gautam.workshop.R
import com.example.gautam.workshop.sharedPreferences.SessionManager
import com.example.gautam.workshop.SQLite.DatabaseHelper


class LoginSignup : AppCompatActivity() {

    val  activity = this@LoginSignup
    var signup: TextView? = null
    var ed1: EditText? = null
    var ed2: EditText? = null
    var userDetail: TextView? = null
    var loginButton: Button? = null
    var databaseHelper: DatabaseHelper? = null
    var sessionManager : SessionManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)

        signup = findViewById(R.id.signupText)
        loginButton = findViewById(R.id.loginButton)
        ed1 = findViewById(R.id.lEmail)
        ed2 = findViewById(R.id.lPassword)
        userDetail = findViewById(R.id.userDetail)
        databaseHelper = DatabaseHelper(activity)
        sessionManager = SessionManager(activity)

        signup?.setOnClickListener({
            var clickIntent = Intent(this@LoginSignup, SignupTextView::class.java)
            startActivity(clickIntent)
        })

         loginButton?.setOnClickListener {
             val user_email = ed1?.text
             val password = ed2?.text
             val value1 = user_email.toString().trim()
             val value2 = password.toString().trim()
             if (value1.isEmpty() || value2.isEmpty()){
                 Toast.makeText(this@LoginSignup, "Enter all details.", Toast.LENGTH_LONG).show()
                 emptyEditText()
                 return@setOnClickListener
             }
             if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value1).matches()){
                 Toast.makeText(this@LoginSignup, "Enter the email correctly.", Toast.LENGTH_LONG).show()
                 emptyEditText()
                 return@setOnClickListener
             }
             if (!databaseHelper!!.checkUser(value1)) {
                 Toast.makeText(this@LoginSignup, "Not Registered.", Toast.LENGTH_LONG).show()
                 emptyEditText()
                 return@setOnClickListener
             }
             if (databaseHelper!!.checkUser(value1, value2)){
                 sessionManager!!.createLoginSession(value1)
                 emptyEditText()
                 Toast.makeText(this@LoginSignup, "Login Successfully", Toast.LENGTH_LONG).show()
                 var clickIntent = Intent(this@LoginSignup, My_Profile::class.java)
                 startActivity(clickIntent)

             }
             else{
                 Toast.makeText(this@LoginSignup, "Wrong Password.", Toast.LENGTH_LONG).show()
                 emptyEditText()
             }
        }
    }

    fun emptyEditText(){
        ed1!!.text = null
        ed2!!.text = null
    }

}



