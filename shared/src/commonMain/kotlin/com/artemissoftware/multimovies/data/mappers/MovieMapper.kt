package com.artemissoftware.multimovies.data.mappers

import com.artemissoftware.multimovies.data.network.dto.MovieDto
import com.artemissoftware.multimovies.domain.models.Movie

internal fun MovieDto.toMovie(): Movie {
    return Movie(
        id = id,
        title = title,
        description = overview,
        imageUrl = getImageUrl(posterImage),
        releaseDate = releaseDate
    )
}

private fun getImageUrl(posterImage: String) = "https://image.tmdb.org/t/p/w500/$posterImage"