package com.project.segunfrancis.currencyconverter.util

import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by SegunFrancis
 *
 * Sealed class that handles state of data in the application
 */

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    class NetworkError(val error: Throwable) : Result<Nothing>() {
        val errorMessage = when(error) {
            is UnknownHostException -> "Check internet connection"
            is SocketTimeoutException -> "Internet might be slow, try again later"
            else -> "Something went wrong"
        }
    }
    class DatabaseError(val error: Throwable) : Result<Nothing>() {
        val errorMessage: String = "Local cache is empty, try syncing with internet connection"
    }
    object Loading : Result<Nothing>()
}