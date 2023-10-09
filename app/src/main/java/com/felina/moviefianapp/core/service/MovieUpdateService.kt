package com.felina.moviefianapp.core.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.felina.moviefianapp.core.domain.repository.IMovieRepository
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MovieUpdateService : Service() {
    private val movieRepository: MovieUseCase by inject()
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        fetchAndSaveMovies()
        return START_STICKY
    }

    private fun fetchAndSaveMovies() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                movieRepository.getAllMovie()
                Log.e("Movie Service Work", movieRepository.toString())
            } catch (e: Exception) {
                Log.e("Movie Service Error", e.toString())
            }
            stopSelf()
        }
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}