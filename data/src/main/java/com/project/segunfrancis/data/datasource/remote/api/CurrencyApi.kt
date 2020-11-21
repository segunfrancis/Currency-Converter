package com.project.segunfrancis.data.datasource.remote.api

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SegunFrancis
 */

interface CurrencyApi {

    @GET("latest")
    suspend fun getCurrency(@Query("access_key") apiKey: String): ResponseBody
}