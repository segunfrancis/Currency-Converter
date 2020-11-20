package com.project.segunfrancis.data.datasource.remote.api

import com.project.segunfrancis.data.datasource.remote.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by SegunFrancis
 */

interface CurrencyApi {

    @GET("latest")
    fun getCurrency(@Query("access_key") apiKey: String): CurrencyResponse
}