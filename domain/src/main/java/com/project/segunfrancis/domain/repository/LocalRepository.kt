package com.project.segunfrancis.domain.repository

import com.project.segunfrancis.domain.model.CurrencyDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */
interface LocalRepository {

    fun getCurrencies(): Flow<CurrencyDomain>

    fun insertCurrencies(currency: CurrencyDomain): Flow<Unit>
}