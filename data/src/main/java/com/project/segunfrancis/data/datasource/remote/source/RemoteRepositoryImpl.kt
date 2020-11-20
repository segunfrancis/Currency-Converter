package com.project.segunfrancis.data.datasource.remote.source

import com.project.segunfrancis.data.datasource.remote.api.CurrencyApi
import com.project.segunfrancis.data.datasource.remote.mapper.CurrencyResponseMapper
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class RemoteRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val currencyResponseMapper: CurrencyResponseMapper
) :
    RemoteRepository {
    override fun getCurrency(apiKey: String): Flow<CurrencyDomain> {
        return flow {
            emit(currencyResponseMapper.mapRemoteToDomain(currencyApi.getCurrency(apiKey)))
        }
    }
}