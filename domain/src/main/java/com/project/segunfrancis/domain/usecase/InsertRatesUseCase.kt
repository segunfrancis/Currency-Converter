package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.model.RatesDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class InsertRatesUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val dispatcher: CoroutineDispatcher
) {
    operator fun invoke(rates: RatesDomain): Flow<Unit> {
        return localRepository.insertRates(rates).flowOn(dispatcher)
    }
}