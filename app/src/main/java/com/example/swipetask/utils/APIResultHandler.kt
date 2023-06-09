package com.example.swipetask.utils

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleAPIResult(networkCall: suspend () -> Response<T>): NetworkResult<T> {

    return try {
        val response = networkCall()
        val body = response.body()

        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}