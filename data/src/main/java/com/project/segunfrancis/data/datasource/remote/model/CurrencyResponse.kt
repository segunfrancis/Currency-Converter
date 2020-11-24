package com.project.segunfrancis.data.datasource.remote.model

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: List<RatesResponse>,
    val success: Boolean,
    val timestamp: Int
)