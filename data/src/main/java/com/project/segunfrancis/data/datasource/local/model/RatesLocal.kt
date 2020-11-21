package com.project.segunfrancis.data.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates_table")
data class RatesLocal(
    val AED: Double,
    val AFN: Double,
    val ALL: Double,
    val AMD: Double,
    @PrimaryKey(autoGenerate = false)
    val EUR: Int
)