package com.project.segunfrancis.data.datasource.local.model

data class CurrencyLocal(
    val base: String,
    val date: String,
    val rates: RatesLocal,
    val success: Boolean,
    val timestamp: Int
)