package com.example.movieapp.data.remote

import com.example.movieapp.data.remote.respond.MoviListDto
import com.example.movieapp.data.remote.respond.MovieDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{category}")
    suspend fun getMovies(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ):MoviListDto


    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val Image_Base_URL = "https://image.tmdb.org/t/p/w500"
        const val API_KEY = "your_api_key_here"
    }

}