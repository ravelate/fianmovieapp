package com.felina.moviefianapp.core.domain.usecase

import androidx.lifecycle.LiveData
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.domain.model.Movie

interface MovieUseCase {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>
}