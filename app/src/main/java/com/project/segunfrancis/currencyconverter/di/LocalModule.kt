package com.project.segunfrancis.currencyconverter.di

import android.content.Context
import androidx.room.Room
import com.project.segunfrancis.currencyconverter.util.AppConstants.DATABASE_NAME
import com.project.segunfrancis.data.datasource.local.db.CurrencyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ApplicationComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}