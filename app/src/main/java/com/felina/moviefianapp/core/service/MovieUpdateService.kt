package com.felina.moviefianapp.core.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.felina.moviefianapp.core.data.Resource.Error
import com.felina.moviefianapp.core.data.Resource.Loading
import com.felina.moviefianapp.core.data.Resource.Success
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase
import org.koin.android.ext.android.inject

class MovieUpdateService : Service() {
    private val movieRepository: MovieUseCase by inject()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        fetchAndSaveMovies()
        return START_STICKY
    }

    private fun fetchAndSaveMovies() {
            try {
                val movie = movieRepository.getAllMovie(false).asLiveData()
                movie.observeForever {
                    if (it != null) {
                        when (it) {
                            is Loading -> { Log.e("Service data loading", it.data.toString()) }
                            is Success -> {
                                val intent = Intent("custom_action")
                                intent.putExtra("message", "Hello from the service!")
                                LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                                Log.e("Movie Service data Work", it.data.toString())
                            }
                            is Error -> {
                                Log.e("Service Data Dont work", it.data.toString())
                            }
                        }
                    }
                }
                Log.e("Movie Service Work", movieRepository.toString())
            } catch (e: Exception) {
                Log.e("Movie Service Error", e.toString())
            }
            stopSelf()
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}