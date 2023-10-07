package com.felina.moviefianapp.core.data.source.local

    import com.felina.moviefianapp.core.data.source.local.entity.MovieEntity
import com.felina.moviefianapp.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {
    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()
    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

}