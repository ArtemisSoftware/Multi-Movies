package com.artemissoftware.multimovies.data.network.dto

import kotlinx.serialization.Serializable

@Serializable
internal data class MoviesDto(
    val results: List<MovieDto>
)