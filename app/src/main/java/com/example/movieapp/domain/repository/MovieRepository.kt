package com.example.movieapp.domain.repository

import com.example.movieapp.domain.model.Movie
import com.example.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovieList(
        forceFetchFromRemote:Boolean,
        category:String,
        page: Int,
    ):Flow<Resource<List<Movie>>>


    suspend fun getMovie(movieId: Int): Flow<Resource<Movie>>
}