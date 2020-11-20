package com.project.segunfrancis.data.datasource.local.source

import com.project.segunfrancis.data.datasource.local.db.CurrencyDatabase
import com.project.segunfrancis.data.datasource.local.mapper.RatesLocalMapper
import com.project.segunfrancis.domain.model.RatesDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class LocalRepositoryImpl @Inject constructor(
    private val database: CurrencyDatabase,
    private val ratesLocalMapper: RatesLocalMapper
) : LocalRepository {
    override fun getAllRates(): Flow<RatesDomain> {
        return flow {
            emit(ratesLocalMapper.mapLocalToDomain(database.currencyDao().getAllRates()))
        }
    }

    override fun insertRates(rates: RatesDomain): Flow<Unit> {
        return flow {
            emit(database.currencyDao().insertRates(ratesLocalMapper.mapDomainToLocal(rates)))
        }
    }
}