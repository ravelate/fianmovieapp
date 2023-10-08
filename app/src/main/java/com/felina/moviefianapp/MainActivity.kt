package com.felina.moviefianapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.felina.moviefianapp.databinding.ActivityMainBinding
import com.felina.moviefianapp.feature.home.HomeFragment
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