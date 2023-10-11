package com.felina.moviefianapp.core.domain.usecase

import com.felina.moviefianapp.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getAllMovie(service: Boolean) = movieRepository.getAllMovie(service)
}