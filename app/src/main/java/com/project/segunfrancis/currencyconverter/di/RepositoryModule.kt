package com.project.segunfrancis.currencyconverter.di

import com.project.segunfrancis.data.datasource.local.source.LocalRepositoryImpl
import com.project.segunfrancis.data.datasource.remote.source.RemoteRepositoryImpl
import com.project.segunfrancis.domain.repository.LocalRepository
import com.project.segunfrancis.domain.repository.RemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by SegunFrancis
 */

@Module
@InstallIn(ActivityRetainedComponent::class)
interface RepositoryModule {

    @Binds
    fun remoteRepositorySource(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Binds
    fun localRepositoryMSource(localRepositoryImpl: LocalRepositoryImpl): LocalRepository
}