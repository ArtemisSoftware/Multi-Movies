package com.artemissoftware.multimovies.android.home

import com.artemissoftware.multimovies.domain.models.Movie

data class HomeState(
    var loading: Boolean = false,
    var refreshing: Boolean = false,
    var movies: List<Movie> = listOf(),
    var errorMessage: String? = null,
    var loadFinished: Boolean = false
){
    fun isEndOfList(index: Int) = index >= movies.size - 1 && !loading && !loadFinished
}
