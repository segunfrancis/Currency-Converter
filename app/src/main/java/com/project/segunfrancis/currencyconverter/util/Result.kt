package com.project.segunfrancis.currencyconverter.util

/**
 * Created by SegunFrancis
 *
 * Sealed class that handles state of data in the application
 */

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    class Error(val error: Throwable) : Result<Nothing>()
    object Loading : Result<Nothing>()
}