package com.project.segunfrancis.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface RemoteRepository {

    fun getCurrency(apiKey: String): Flow<String>
}