package com.project.segunfrancis.data.datasource.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.project.segunfrancis.data.datasource.local.model.RatesLocal

/**
 * Created by SegunFrancis
 */

class Converters {

    @TypeConverter
    fun stringToList(data: String): List<RatesLocal> {
        val type = object : TypeToken<List<RatesLocal>>() {}.type
        return Gson().fromJson(data, type)
    }

    @TypeConverter
    fun listToString(data: List<RatesLocal>): String {
        return Gson().toJson(data)
    }
}