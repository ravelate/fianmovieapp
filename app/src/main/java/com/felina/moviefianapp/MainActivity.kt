package com.felina.moviefianapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import androidx.appcompat.app.AppCompatActivity
import com.felina.moviefianapp.core.service.MovieUpdateService
import com.felina.moviefianapp.databinding.ActivityMainBinding
import com.felina.moviefianapp.feature.splash.SplashFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val alarmManager: AlarmManager by lazy {
        getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alarmIntent = Intent(applicationContext, MovieUpdateService::class.java)
        val pendingIntent = PendingIntent.getService(
            applicationContext,
            0,
            alarmIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val intervalMillis = 10 * 100 // 1 minute
        val firstTriggerMillis = SystemClock.elapsedRealtime() + intervalMillis

        alarmManager.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            firstTriggerMillis,
            intervalMillis.toLong(),
            pendingIntent
        )

        supportFragmentManager.beginTransaction()
            .replace(binding.fcvFragment.id, SplashFragment())
            .commit()

        applicationContext.startService(alarmIntent)
    }
}