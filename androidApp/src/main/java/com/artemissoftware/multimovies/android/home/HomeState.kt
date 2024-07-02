package com.artemissoftware.multimovies.android.home

import com.artemissoftware.multimovies.domain.models.Movie

data class HomeState(
    val loading: Boolean = false,
    val refreshing: Boolean = false,
    val movies: List<Movie> = listOf(),
    val errorMessage: String? = null,
    val loadFinished: Boolean = false,
    val currentPage: Int = 1
){
    fun isEndOfList(index: Int) = index >= movies.size - 1 && !loading && !loadFinished
}
