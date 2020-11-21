package com.project.segunfrancis.data.datasource.remote.source

import com.project.segunfrancis.data.datasource.remote.api.CurrencyApi
import com.project.segunfrancis.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class RemoteRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi
) :
    RemoteRepository {
    override fun getCurrency(apiKey: String): Flow<String> {
        return flow {
            emit(currencyApi.getCurrency(apiKey).string())
        }
    }
}