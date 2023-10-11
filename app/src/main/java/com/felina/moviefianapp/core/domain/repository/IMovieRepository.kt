package com.felina.moviefianapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(service: Boolean): Flow<Resource<List<Movie>>>


}