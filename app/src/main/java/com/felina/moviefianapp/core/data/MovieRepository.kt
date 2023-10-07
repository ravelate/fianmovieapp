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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                appExecutors.diskIO().execute {
                    localDataSource.insertMovie(tourismList)
                }
            }
        }.asFlow()
}