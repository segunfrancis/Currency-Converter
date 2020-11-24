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
class GetCurrencyLocalUseCase @Inject constructor(
    private val localRepository: LocalRepository,
    private val dispatcher: CoroutineDispatcher
) {
    fun execute(): Flow<CurrencyDomain> {
        return localRepository.getCurrencies().flowOn(dispatcher)
    }
}