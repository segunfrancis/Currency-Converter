package com.project.segunfrancis.currencyconverter.model

data class Currency(
    val base: String,
    val date: String,
    val rates: List<Rates>,
    val success: Boolean,
    val timestamp: Int
)