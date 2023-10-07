package com.felina.moviefianapp.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.felina.moviefianapp.core.data.source.local.LocalDataSource
import com.felina.moviefianapp.core.data.source.remote.RemoteDataSource
import com.felina.moviefianapp.core.data.source.remote.network.ApiResponse
import com.felina.moviefianapp.core.data.source.remote.response.MovieResponse
import com.felina.moviefianapp.core.domain.model.Movie
import com.felina.moviefianapp.core.domain.repository.IMovieRepository
import com.felina.moviefianapp.core.utils.AppExecutors
import com.felina.moviefianapp.core.utils.DataMapper

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
}