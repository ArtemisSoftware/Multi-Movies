package com.artemissoftware.multimovies.data.network.dto
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
internal data class MovieDto(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterImage: String,
    @SerialName("release_date")
    val releaseDate: String
)