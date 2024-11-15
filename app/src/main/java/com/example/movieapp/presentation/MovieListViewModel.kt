package com.example.movieapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.util.Category
import com.example.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
):ViewModel() {
    private var _movieListState= MutableStateFlow(MovieState())
    val movieListState= _movieListState.asStateFlow()


    init {
        getPopularMovieList(false)
        getUpcomingMovieList(false)
    }

    fun onEvent(event: MovieListUiEvent){
        when(event){
            is MovieListUiEvent.Navigate -> {
                _movieListState.update {
                    it.copy(
                        isCurrentPopularScreen = !movieListState.value.isCurrentPopularScreen
                    )
                }

            }
            is MovieListUiEvent.Paginate -> {
                if(event.category==Category.POPULAR){
                    getPopularMovieList(true)
                }else if(event.category==Category.UPCOMING){
                    getUpcomingMovieList(true)
                }
            }
        }
    }

    private fun getUpcomingMovieList(forceFetchFromRemote:Boolean) {
        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieRepository.getMovieList(
                forceFetchFromRemote,
                Category.UPCOMING,
                movieListState.value.upcomingMovieListPage
            ).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }

                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }

                    is Resource.Success -> {
                        result.data?.let { upcumingMovieList ->
                            _movieListState.update {
                                it.copy(
                                    upcomingMovieList = movieListState.value.upcomingMovieList
                                            + upcumingMovieList.shuffled(),
                                    upcomingMovieListPage = movieListState.value.upcomingMovieListPage + 1
                                )
                            }
                        }
                    }
                }

            }
        }
    }

    private fun getPopularMovieList(forceFetchFromRemote:Boolean) {

        viewModelScope.launch {
            _movieListState.update {
                it.copy(isLoading = true)
            }

            movieRepository.getMovieList(
                forceFetchFromRemote,
                Category.POPULAR,
                movieListState.value.popularMovieListPage
            ).collectLatest { result ->
                when(result){
                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is Resource.Success -> {
                            result.data?.let{ popularMovieList ->
                                _movieListState.update {
                                    it.copy(
                                        popularMovieList = movieListState.value.popularMovieList
                                        + popularMovieList.shuffled()
                                        ,
                                        popularMovieListPage = movieListState.value.popularMovieListPage + 1

                                    )
                                }

                            }
                    }
                }
                    Log.d("MyLog", result.toString())

            }
        }
    }


}