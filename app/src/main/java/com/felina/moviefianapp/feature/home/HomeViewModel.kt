package com.felina.moviefianapp.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase
class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()
}