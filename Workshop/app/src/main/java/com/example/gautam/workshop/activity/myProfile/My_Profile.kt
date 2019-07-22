package com.example.gautam.workshop.activity.myProfile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.gautam.workshop.activity.MainActivity
import com.example.gautam.workshop.R
import com.example.gautam.workshop.sharedPreferences.SessionManager
import com.example.gautam.workshop.SQLite.DatabaseHelper

class My_Profile : AppCompatActivity() {

    val activity = this@My_Profile
    var ed: TextView? = null
    var logInButton: Button? = null
    var mainScreenText: TextView? = null
    var databaseHelper: DatabaseHelper? = null
    var sessionManager : SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my__profile)

        logInButton = findViewById(R.id.logInButton)
        mainScreenText = findViewById(R.id.mainScreenText)
        ed = findViewById(R.id.userDetail)
        databaseHelper = DatabaseHelper(activity)
        sessionManager = SessionManager(activity)

        if(sessionManager!!.checkLogin()) {
            var data = databaseHelper!!.getCurrentUser()
            ed?.text = ""
            logInButton?.text = "Log Out"
            for (i in 0..(data.size-1)) {
                if(data.get(i).email.equals(sessionManager!!.userDeatil())) {
                    ed?.append(data.get(i).name + "\n" + data.get(i).email)
                }
            }
        } else {
            ed?.text = "User Details......"
            logInButton?.text = "Log In"
        }

        logInButton?.setOnClickListener({
            if (!sessionManager!!.checkLogin()) {
                var clickIntent = Intent(this@My_Profile, LoginSignup::class.java)
                startActivity(clickIntent)
            } else {
                if (sessionManager!!.checkLogin()) {
                    sessionManager!!.logoutUser()
                    ed?.text = "User Details......"
                    logInButton?.text = "Log In"
                    Toast.makeText(this@My_Profile, "Log Out Successfully.", Toast.LENGTH_LONG).show()
                }
            }
        })

        mainScreenText?.setOnClickListener({
            var clickIntent = Intent(this@My_Profile, MainActivity::class.java)
            startActivity(clickIntent)
        })

    }
}
