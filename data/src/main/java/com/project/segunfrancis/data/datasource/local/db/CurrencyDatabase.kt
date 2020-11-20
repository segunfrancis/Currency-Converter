package com.project.segunfrancis.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.segunfrancis.data.datasource.local.model.RatesLocal

/**
 * Created by SegunFrancis
 */

@Database(entities = [RatesLocal::class], exportSchema = false, version = 1)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}