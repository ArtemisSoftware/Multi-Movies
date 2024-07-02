package com.artemissoftware.multimovies.android.detail

import com.artemissoftware.multimovies.domain.models.Movie

data class DetailState(
    var loading: Boolean = false,
    var movie: Movie? = null,
    var errorMessage: String? = null
)
