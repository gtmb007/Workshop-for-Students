package com.example.gautam.workshop.activity.Developer

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.gautam.workshop.R
import com.example.gautam.workshop.SQLite.DatabaseHelper1

class DeleteAllWorkshops : AppCompatActivity() {

    val  activity = this@DeleteAllWorkshops
    var no: TextView? = null
    var yes: TextView? = null
    var databaseHelper1: DatabaseHelper1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_all_workshops)

        no = findViewById(R.id.no)
        yes = findViewById(R.id.yes)
        databaseHelper1 = DatabaseHelper1(activity)

        no?.setOnClickListener {
            var clickIntent = Intent(this@DeleteAllWorkshops, DeveloperOption::class.java)
            startActivity(clickIntent)
        }

        yes?.setOnClickListener {
            databaseHelper1!!.deleteAllWorkshops()
            Toast.makeText(this@DeleteAllWorkshops, "Deleted Successfully.", Toast.LENGTH_LONG).show()
            var clickIntent = Intent(this@DeleteAllWorkshops, DeveloperOption::class.java)
            startActivity(clickIntent)
        }
    }
}
