package com.example.gautam.workshop.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.gautam.workshop.R
import com.example.gautam.workshop.SQLite.DatabaseHelper1
import com.example.gautam.workshop.sharedPreferences.SessionManager

class WorkshopsButton : AppCompatActivity() {

    val  activity = this@WorkshopsButton
    var tvResult: TextView? = null
    var sessionManager : SessionManager? = null
    var databaseHelper1: DatabaseHelper1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workshops_button)

        sessionManager = SessionManager(activity)
        databaseHelper1 = DatabaseHelper1(activity)
        tvResult = findViewById(R.id.tvResult)

        var data = databaseHelper1!!.getAllWorkshops()
        tvResult?.text = ""
        for (i in 0..(data.size-1)){
            tvResult?.append(data.get(i).workshopName + "\n\t\t\tOrganised by - " + data.get(i).organisationName + "\nAPPLY\n\n")
        }

    }
}