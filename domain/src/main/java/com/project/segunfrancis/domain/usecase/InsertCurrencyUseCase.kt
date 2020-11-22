package com.project.segunfrancis.domain.usecase

import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class InsertCurrencyUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(currency: CurrencyDomain): Flow<Unit> {
        return localRepository.insertCurrencies(currency).flowOn(dispatcher)
    }
}