package com.felina.moviefianapp.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase

class SplashViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie(true).asLiveData()
}