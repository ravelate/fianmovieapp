package com.felina.moviefianapp.core.domain.usecase

import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
}