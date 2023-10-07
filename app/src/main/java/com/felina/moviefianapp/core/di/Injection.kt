package com.felina.moviefianapp.core.di

import android.content.Context
import com.felina.moviefianapp.core.data.MovieRepository
import com.felina.moviefianapp.core.data.source.local.LocalDataSource
import com.felina.moviefianapp.core.data.source.local.room.MovieDatabase
import com.felina.moviefianapp.core.data.source.remote.RemoteDataSource
import com.felina.moviefianapp.core.data.source.remote.network.ApiConfig
import com.felina.moviefianapp.core.domain.usecase.MovieInteractor
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase
import com.felina.moviefianapp.core.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): MovieRepository {
        val database = MovieDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
    fun provideMovieUseCase(context: Context): MovieUseCase {
        val repository = provideRepository(context)
        return MovieInteractor(repository)
    }
}
