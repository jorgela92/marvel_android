package com.jorgel.marvel.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jorgel.marvel.R
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SplashActivity: AppCompatActivity() {
    private val delay: Long = 1500

    private val worker = Executors.newSingleThreadScheduledExecutor()
    private var mRunnable = Runnable {
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        worker.schedule(mRunnable, delay, TimeUnit.MILLISECONDS)
    }

    public override fun onDestroy() {
        worker.shutdown()
        super.onDestroy()
    }
}
