package com.felina.moviefianapp.core.di

import com.felina.moviefianapp.core.data.MovieRepository
import com.felina.moviefianapp.core.data.source.local.LocalDataSource
import com.felina.moviefianapp.core.data.source.remote.RemoteDataSource
import com.felina.moviefianapp.core.domain.repository.IMovieRepository
import com.felina.moviefianapp.core.utils.AppExecutors
import androidx.room.Room
import com.felina.moviefianapp.core.data.source.remote.network.ApiService
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import com.felina.moviefianapp.core.data.source.local.room.MovieDatabase


val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
//            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
    single {

        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}