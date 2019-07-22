package com.example.gautam.workshop.activity.Developer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.gautam.workshop.R
import com.example.gautam.workshop.SQLite.DatabaseHelper1
import com.example.gautam.workshop.activity.MainActivity
import com.example.gautam.workshop.model.Workshop

class DeveloperOption : AppCompatActivity() {

    val  myActivity = this@DeveloperOption
    var clickHere: TextView? = null
    var addButton: Button? = null
    var mainScreenText: TextView? = null
    var ed1: EditText? = null
    var ed2: EditText? = null
    var databaseHelper1: DatabaseHelper1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_developer_option)

        clickHere = findViewById(R.id.clickHere)
        addButton = findViewById(R.id.addButton)
        mainScreenText = findViewById(R.id.mainScreenText)
        ed1 = findViewById(R.id.workshopName)
        ed2 = findViewById(R.id.organisationName)
        databaseHelper1 = DatabaseHelper1(myActivity)

        clickHere?.setOnClickListener {
            var clickIntent = Intent(this@DeveloperOption, DeleteAllWorkshops::class.java)
            startActivity(clickIntent)
        }

        addButton?.setOnClickListener {
            val workshopName = ed1?.text
            val organisationName = ed2?.text
            val value1 = workshopName.toString().trim()
            val value2 = organisationName.toString().trim()

            if (value1.isEmpty() || value2.isEmpty()) {
                Toast.makeText(this@DeveloperOption, "Enter workshop details.", Toast.LENGTH_LONG).show()
                emptyEditText()
                return@setOnClickListener
            }
            if (databaseHelper1!!.checkWorkshop(value1, value2)) {
            Toast.makeText(this@DeveloperOption, "Already Exists.", Toast.LENGTH_LONG).show()
            emptyEditText()
            } else {

                var workshop = Workshop()
                workshop.workshopName = value1
                workshop.organisationName = value2

                databaseHelper1!!.addWorkshop(workshop)
                Toast.makeText(this@DeveloperOption, "Addeed Successfully.", Toast.LENGTH_LONG).show()
                emptyEditText()
            }
        }

        mainScreenText?.setOnClickListener({
            var clickIntent = Intent(this@DeveloperOption, MainActivity::class.java)
            startActivity(clickIntent)
        })
    }

    fun emptyEditText(){
        ed1!!.text = null
        ed2!!.text = null
    }
}
