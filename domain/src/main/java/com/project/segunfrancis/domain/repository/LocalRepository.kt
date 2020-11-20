package com.project.segunfrancis.domain.repository

import com.project.segunfrancis.domain.model.RatesDomain
import kotlinx.coroutines.flow.Flow

/**
 * Created by SegunFrancis
 */
interface LocalRepository {

    fun getAllRates(): Flow<RatesDomain>

    fun insertRates(rates: RatesDomain): Flow<Unit>
}