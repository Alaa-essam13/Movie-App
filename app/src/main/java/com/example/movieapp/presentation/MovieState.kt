package com.example.movieapp.presentation

import com.example.movieapp.domain.model.Movie

data class MovieState(
    val isLoading:Boolean=false,
    val popularMovieListPage:Int=1,
    val upcomingMovieListPage:Int=1,
    val isCurrentPopularScreen:Boolean=true,
    val popularMovieList:List<Movie> = emptyList(),
    val upcomingMovieList:List<Movie> = emptyList(),

)
