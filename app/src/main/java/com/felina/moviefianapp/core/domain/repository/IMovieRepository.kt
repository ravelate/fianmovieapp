package com.felina.moviefianapp.core.domain.repository

import androidx.lifecycle.LiveData
import com.felina.moviefianapp.core.data.Resource
import com.felina.moviefianapp.core.domain.model.Movie

interface IMovieRepository {

    fun getAllMovie(): LiveData<Resource<List<Movie>>>


}