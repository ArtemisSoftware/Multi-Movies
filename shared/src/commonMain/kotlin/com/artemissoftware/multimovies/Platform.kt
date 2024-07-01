package com.artemissoftware.multimovies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform