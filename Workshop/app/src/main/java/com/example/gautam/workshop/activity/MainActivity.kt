package com.example.gautam.workshop.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.gautam.workshop.R
import com.example.gautam.workshop.SQLite.DatabaseHelper1
import com.example.gautam.workshop.activity.Developer.DeveloperOption
import com.example.gautam.workshop.activity.myProfile.My_Profile

class MainActivity : AppCompatActivity() {

    val  activity = this@MainActivity
    var databaseHelper1: DatabaseHelper1? = null
    var workshopsButton: Button? = null
    var myProfile: Button? = null
    var aboutUsButton: Button? = null
    var developerOption: TextView? = null
    var tvResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workshopsButton = findViewById(R.id.workshopsButton)
        myProfile = findViewById(R.id.myProfile)
        aboutUsButton = findViewById(R.id.aboutUsButton)
        developerOption = findViewById(R.id.developerOption)
        databaseHelper1 = DatabaseHelper1(activity)
        tvResult = findViewById(R.id.tvResult)

        workshopsButton?.setOnClickListener({
            var clickIntent = Intent(this@MainActivity, WorkshopsButton::class.java)
            startActivity(clickIntent)
        })
        
        myProfile?.setOnClickListener({
            var clickIntent = Intent(this@MainActivity, My_Profile::class.java)
            startActivity(clickIntent)
        })

        aboutUsButton?.setOnClickListener({
            var clickIntent = Intent(this@MainActivity, AboutUs::class.java)
            startActivity(clickIntent)
        })

            developerOption?.setOnClickListener({
            var clickIntent = Intent(this@MainActivity, DeveloperOption::class.java)
            startActivity(clickIntent)
        })
    }
}
