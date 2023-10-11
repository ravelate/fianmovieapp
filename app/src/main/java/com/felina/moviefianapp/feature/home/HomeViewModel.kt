package com.felina.moviefianapp.feature.home

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase
import com.felina.moviefianapp.core.service.MovieUpdateService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie(true).asLiveData()
}
