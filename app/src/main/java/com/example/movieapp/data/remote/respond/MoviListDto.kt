package com.example.movieapp.data.remote.respond

data class MoviListDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)