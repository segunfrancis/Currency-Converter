package com.project.segunfrancis.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.project.segunfrancis.data.datasource.local.model.CurrencyLocal

/**
 * Created by SegunFrancis
 */

@Database(entities = [CurrencyLocal::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}