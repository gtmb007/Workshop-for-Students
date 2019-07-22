package com.example.gautam.workshop.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.gautam.workshop.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val startAct = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(startAct)
            this.finish()
        }, 1000)
    }

}
