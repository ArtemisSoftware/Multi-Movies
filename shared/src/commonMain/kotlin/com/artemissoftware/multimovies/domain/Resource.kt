package com.artemissoftware.multimovies.domain

sealed interface Resource<T> {
    data class Success<T>(val data: T) : Resource<T>
    data class Failure<T>(val exception: Exception) : Resource<T>

    fun onSuccess(block: (T) -> Unit): Resource<T> {
        if (this is Success) block(data)
        return this
    }

    fun onFailure(block: (Exception) -> Unit): Resource<T> {
        if (this is Failure) block(exception)
        return this
    }
}