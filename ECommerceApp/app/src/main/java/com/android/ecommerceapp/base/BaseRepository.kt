package com.android.ecommerceapp.base

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {


    suspend fun <T> safeApiCall(apiCall: suspend () -> T): T? {

        return withContext(Dispatchers.IO) {

            try {
                val response = apiCall.invoke()
                println("safe api call invoke")
                response
            } catch (e: Exception) {
                throw parseErrorResponse(e)
            }
        }

    }

    private fun parseErrorResponse(e: Exception): Exception {

        return when (e) {
            is HttpException -> {
                val errorResponse = e.response()?.errorBody()?.string()
                //val errorBody = Gson().fromJson(errorResponse, ErrorResponse::class.java)
                // BaseException(code = e.code(), error = errorBody?.errors?.entrySet()?.toString())
                e
            }

            else -> e
        }

    }

}