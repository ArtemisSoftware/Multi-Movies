package com.artemissoftware.multimovies.data.network

import com.artemissoftware.multimovies.domain.Resource

internal object HandleApi {

    inline fun <T> safeApiCall(apiCall: () -> T): Resource<T> {
        return try {
            Resource.Success(data = apiCall())
        } catch (e: Exception) {
            Resource.Failure(exception = e)
        }
    }
}
