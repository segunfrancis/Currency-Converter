package com.project.segunfrancis.data.datasource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.project.segunfrancis.data.datasource.local.model.CurrencyLocal

/**
 * Created by SegunFrancis
 */

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency_table")
    fun getAllRates(): CurrencyLocal

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(rates: CurrencyLocal)
}