package com.example.movieapp.presentation

sealed interface MovieListUiEvent {

    data class Paginate(val category:String) : MovieListUiEvent

    object Navigate : MovieListUiEvent
}