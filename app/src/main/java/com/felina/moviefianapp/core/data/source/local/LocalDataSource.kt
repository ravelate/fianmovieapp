package com.felina.moviefianapp.core.data.source.local

import androidx.lifecycle.LiveData
import com.felina.moviefianapp.core.data.source.local.entity.MovieEntity
import com.felina.moviefianapp.core.data.source.local.room.MovieDao

class LocalDataSource private constructor(private val movieDao: MovieDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(movieDao)
            }
    }

    fun getAllMovie(): LiveData<List<MovieEntity>> = movieDao.getAllMovie()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

}