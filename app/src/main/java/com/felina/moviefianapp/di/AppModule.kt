package com.felina.moviefianapp.di

import com.felina.moviefianapp.core.domain.usecase.MovieInteractor
import com.felina.moviefianapp.core.domain.usecase.MovieUseCase
import com.felina.moviefianapp.feature.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}