package com.felina.moviefianapp.core.data.source.remote.network

import com.felina.moviefianapp.core.data.source.remote.response.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzM2NlMzA1ZDE0N2U5OGUxZWZiYzU5NjhiZDViY2I2ZiIsInN1YiI6IjY0ZjAxOTIwY2FhNTA4MDBhYjcxYTYxNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.RF0gau9Eb_vhnV4BKFB-gRGMiL3a3smc5UW9xet9hyI")
    @GET("movie/popular")
    fun getList(): Call<ListMovieResponse>
}