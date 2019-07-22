package com.example.gautam.workshop.activity.myProfile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.gautam.workshop.R
import com.example.gautam.workshop.model.User
import com.example.gautam.workshop.SQLite.DatabaseHelper

class SignupTextView : AppCompatActivity() {

    val  activity = this@SignupTextView
    var signupButton: Button? = null
    var loginText: TextView? = null
    var ed1: EditText? = null
    var ed2: EditText? = null
    var ed3: EditText? = null
    var ed4 :EditText? = null
    var databaseHelper: DatabaseHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_text_view)

        signupButton = findViewById(R.id.signupButton)
        loginText = findViewById(R.id.loginText)
        ed1 = findViewById(R.id.sEmail)
        ed2 = findViewById(R.id.sPassword)
        ed3 = findViewById(R.id.cPassword)
        ed4 = findViewById(R.id.name)
        databaseHelper = DatabaseHelper(activity)

        loginText?.setOnClickListener({
            var clickIntent = Intent(this@SignupTextView, LoginSignup::class.java)
            startActivity(clickIntent)
        })

        signupButton?.setOnClickListener {

            val user_email = ed1?.text
            val password = ed2?.text
            val cPassword = ed3?.text
            val user_name = ed4?.text
            val value1 = user_email.toString().trim()
            val value2 = password.toString().trim()
            val value3 = cPassword.toString().trim()
            val value4 = user_name.toString().trim()

            if (value1.isEmpty() || value2.isEmpty() || value3.isEmpty() || value4.isEmpty()) {
                Toast.makeText(this@SignupTextView, "Please enter all details.", Toast.LENGTH_LONG).show()
                emptyEditText()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(value1).matches()){
                Toast.makeText(this@SignupTextView, "Enter the email correctly.", Toast.LENGTH_LONG).show()
                emptyEditText()
                return@setOnClickListener
            }
            if (!value2.contentEquals(value3)){
                Toast.makeText(this@SignupTextView, "Password not matched correctly.", Toast.LENGTH_LONG).show()
                emptyEditText()
                return@setOnClickListener
            }
            if (databaseHelper!!.checkUser(value1)) {
             Toast.makeText(this@SignupTextView, "Already Registered.", Toast.LENGTH_LONG).show()
                emptyEditText()
            } else {
                var user = User()
                user.name = value4
                user.email = value1
                user.password = value2

                databaseHelper!!.addUser(user)

                Toast.makeText(this@SignupTextView, "Registered Successfully.", Toast.LENGTH_LONG).show()
                emptyEditText()
                var clickIntent = Intent(this@SignupTextView, LoginSignup::class.java)
                startActivity(clickIntent)
            }
        }
    }

    fun emptyEditText(){
        ed1!!.text = null
        ed2!!.text = null
        ed3!!.text = null
        ed4!!.text = null
    }

}
