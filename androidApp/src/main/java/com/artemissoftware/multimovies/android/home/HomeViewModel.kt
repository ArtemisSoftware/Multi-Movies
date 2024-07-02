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

    init {
        loadMovies()
    }

    fun onTriggerEvent(event: HomeEvent){
        when(event){
            HomeEvent.LoadNextMovies -> { loadMovies() }
            HomeEvent.ReloadMovies -> reloadMovies()
        }
    }

    private fun loadMovies() = with(_state){
        viewModelScope.launch {
            setLoading()

            getMoviesUseCase(page = value.currentPage)
                .onSuccess { result ->
                    val movies = if (value.currentPage == 1) result else value.movies + result

                    update {
                        it.copy(
                            currentPage = it.currentPage + 1,
                            loading = false,
                            refreshing = false,
                            loadFinished = result.isEmpty(),
                            movies = movies
                        )
                    }
                }
                .onFailure { error ->
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

    private fun reloadMovies() = with(_state){
        if (!value.loading) {
            update {
                it.copy(
                    currentPage = 1,
                    refreshing = true,
                )
            }
            loadMovies()
        }
    }

    private fun setLoading(isLoading: Boolean = true) = with(_state){
        update {
            it.copy(loading = isLoading)
        }
    }
}