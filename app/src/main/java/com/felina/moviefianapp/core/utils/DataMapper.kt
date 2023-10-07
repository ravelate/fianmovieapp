package com.felina.moviefianapp.core.utils

import com.felina.moviefianapp.core.data.source.local.entity.MovieEntity
import com.felina.moviefianapp.core.data.source.remote.response.MovieResponse
import com.felina.moviefianapp.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val tourismList = ArrayList<MovieEntity>()
        input.map {
            val tourism = MovieEntity(
                id = it.id,
                overview = it.overview,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
            )
            tourismList.add(tourism)
        }
        return tourismList
    }
    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                overview = it.overview,
                video = it.video,
                title = it.title,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        overview = input.overview,
        video = input.video,
        title = input.title,
        posterPath = input.posterPath,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
    )
}