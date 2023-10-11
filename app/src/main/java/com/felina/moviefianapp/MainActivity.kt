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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.fcvFragment.id, SplashFragment())
            .commit()
    }
}