package com.artemissoftware.multimovies.android.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.multimovies.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
): ViewModel(){

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private var currentPage = 1

    init {
        loadMovies()
    }

    private fun loadMovies() = with(_state){
        viewModelScope.launch {
            setLoading()

            try {
                val result = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) result else value.movies + result

                currentPage += 1

                update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        loadFinished = result.isEmpty(),
                        movies = movies
                    )
                }
            } catch (error: Throwable){
                update {
                    it.copy(
                        loading = false,
                        refreshing = false,
                        loadFinished = true,
                        errorMessage = "Could not load movies: ${error.localizedMessage}"
                    )
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean = true) = with(_state){
        update {
            it.copy(loading = isLoading)
        }
    }

/*
    private fun loadMovies(forceReload: Boolean = false){
        if (uiState.loading) return
        if (forceReload) currentPage = 1
        if (currentPage == 1) uiState = uiState.copy(refreshing = true)

        viewModelScope.launch {
            uiState = uiState.copy(
                loading = true
            )

            try {
                val resultMovies = getMoviesUseCase(page = currentPage)
                val movies = if (currentPage == 1) resultMovies else uiState.movies + resultMovies

                currentPage += 1
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = resultMovies.isEmpty(),
                    movies = movies
                )

            }catch (error: Throwable){
                uiState = uiState.copy(
                    loading = false,
                    refreshing = false,
                    loadFinished = true,
                    errorMessage = "Could not load movies: ${error.localizedMessage}"
                )
            }
        }
    }
    */
}