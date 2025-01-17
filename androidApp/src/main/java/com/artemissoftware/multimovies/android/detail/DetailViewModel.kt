package com.artemissoftware.multimovies.android.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemissoftware.multimovies.android.navigation.NavArguments
import com.artemissoftware.multimovies.domain.usecase.GetMovieUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getMovieUseCase: GetMovieUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>(NavArguments.MOVIE_ID)?.let {
            loadMovie(it)
        }
    }

    private fun loadMovie(movieId: Int) = with(_state){
        viewModelScope.launch {
            setLoading()

            getMovieUseCase(movieId = movieId)
                .onSuccess { movie ->
                    update {
                        it.copy(
                            loading = false,
                            movie = movie,
                        )
                    }
                }
                .onFailure { error ->
                    update {
                        it.copy(
                            loading = false,
                            errorMessage = "Could not load the movie"
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
}