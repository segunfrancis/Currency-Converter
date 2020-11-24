package com.project.segunfrancis.data.datasource.remote.mapper

import com.project.segunfrancis.data.datasource.remote.model.CurrencyResponse
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.model.RatesDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class CurrencyRemoteMapper @Inject constructor() : Mapper<CurrencyResponse, CurrencyDomain> {
    override fun mapLocalToDomain(data: CurrencyResponse): CurrencyDomain {
        return with(data) {
            CurrencyDomain(base, date, with(rates) {
                this.map {
                    RatesDomain(it.currencyCode, it.exchangeRate)
                }
            }, success, timestamp)
        }
    }
}