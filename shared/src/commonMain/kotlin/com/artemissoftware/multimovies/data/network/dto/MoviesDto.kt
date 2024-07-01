package com.artemissoftware.multimovies.data.network.dto

@kotlinx.serialization.Serializable
internal class MoviesDto(
    val results: List<MovieDto>
)