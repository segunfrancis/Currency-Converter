package com.project.segunfrancis.domain.repository

import com.project.segunfrancis.domain.model.CurrencyDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */

interface RemoteRepository {

    fun getCurrency(apiKey: String): Flow<CurrencyDomain>
}