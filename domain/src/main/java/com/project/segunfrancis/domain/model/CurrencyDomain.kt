package com.project.segunfrancis.domain.model

data class CurrencyDomain(
    val base: String,
    val date: String,
    val rates: List<RatesDomain>,
    val success: Boolean,
    val timestamp: Int
)