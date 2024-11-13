package com.example.movieapp.data.mapper

import com.example.movieapp.data.local.Movie.MovieEntity
import com.example.movieapp.data.remote.respond.MovieDto
import com.example.movieapp.domain.model.Movie

fun MovieEntity.toMovie(
    category: String
):Movie{
    return Movie(
        backdrop_path = backdrop_path,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        title = title,
        vote_average = vote_average,
        vote_count = vote_count,
        video = video,
        release_date = release_date,
        category = category,
        id = id,
        adult = adult,
        genre_ids = try{
            genre_ids.split(",").map { it.toInt() }
        }catch (e:Exception){
           listOf(-1,-2)
        }
    )
}


fun MovieDto.toMovieEntity(category: String): MovieEntity{
    return MovieEntity(
        backdrop_path = backdrop_path?: "",
        original_language = original_language ?: "",
        original_title = original_title ?: "",
        overview = overview ?: "",
        popularity = popularity ?: 0.0,
        poster_path = poster_path ?: "",
        title = title ?: "",
        vote_average = vote_average?: 0.0,
        vote_count = vote_count?: 0,
        video = video ?: false,
        release_date = release_date ?: "",
        category = category,
        id = id ?: -1,
        adult = adult ?: false,
       genre_ids = try {
           genre_ids?.joinToString(",")?:"-1,-2"
       }catch (e:Exception){
           "-1,-2"
       }
    )
}