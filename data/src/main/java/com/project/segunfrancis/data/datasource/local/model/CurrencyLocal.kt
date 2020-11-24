package com.project.segunfrancis.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyLocal(
    val base: String,
    val date: String,
    val rates: List<RatesLocal>,
    val success: Boolean,
    @PrimaryKey val timestamp: Int
)