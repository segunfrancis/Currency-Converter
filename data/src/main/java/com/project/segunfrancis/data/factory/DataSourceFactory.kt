package com.project.segunfrancis.data.factory

import com.project.segunfrancis.domain.repository.LocalRepository
import com.project.segunfrancis.domain.repository.RemoteRepository

/**
 * Created by SegunFrancis
 */

class DataSourceFactory(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository
) {
    fun local() = localRepository
    fun remote() = remoteRepository
}