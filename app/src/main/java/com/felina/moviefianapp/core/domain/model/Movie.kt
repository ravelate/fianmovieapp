package com.felina.moviefianapp.core.domain.model
data class Movie(
    val id: Int,
    val overview: String,
    val video: Boolean,
    val title: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Double,
    val voteCount: Int,
)