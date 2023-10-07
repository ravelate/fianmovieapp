package com.felina.moviefianapp.feature

import androidx.lifecycle.ViewModel
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase

class HomeViewModel(movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie()
}