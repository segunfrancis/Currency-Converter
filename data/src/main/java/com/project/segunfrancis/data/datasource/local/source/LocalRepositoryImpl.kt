package com.project.segunfrancis.data.datasource.local.source

import com.project.segunfrancis.data.datasource.local.db.CurrencyDatabase
import com.project.segunfrancis.data.datasource.local.mapper.CurrencyLocalMapper
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class LocalRepositoryImpl @Inject constructor(
    private val database: CurrencyDatabase,
    private val ratesLocalMapper: CurrencyLocalMapper
) : LocalRepository {
    override fun getCurrencies(): Flow<CurrencyDomain> {
        return flow {
            emit(ratesLocalMapper.mapLocalToDomain(database.currencyDao().getAllRates()))
        }
    }

    override fun insertCurrencies(currency: CurrencyDomain): Flow<Unit> {
        return flow {
            emit(database.currencyDao().insertRates(ratesLocalMapper.mapDomainToLocal(currency)))
        }
    }
}