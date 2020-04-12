package com.tapan.facts.data.core

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * Base repository super class for all repository contains methods for wrapping API calls in Either
 */
open class BaseRepository {
    suspend fun <R> executeSafeApiCall(
        autoHandle: Boolean = true,
        data: suspend () -> R
    ): Either<MyException, R> {


        return try {
            val response = data.invoke()
            Either.Right(response)
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(convertToMyException(e))
        }

    }

    private fun convertToMyException(e: Exception): MyException {
        return when (e) {
            is UnknownHostException -> MyException.NetworkErrorError(e)
            is HttpException -> getHttpException(e)
            else -> MyException.UnKnownError(e)
        }
    }

    private fun getHttpException(e: HttpException): MyException {
        return MyException.UnKnownError(RuntimeException(e.message()    ))
    }

    companion object {
        val gson = Gson();
    }
}