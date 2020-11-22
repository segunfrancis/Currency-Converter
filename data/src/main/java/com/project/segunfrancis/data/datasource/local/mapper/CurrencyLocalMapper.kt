package com.project.segunfrancis.data.datasource.local.mapper

import com.project.segunfrancis.data.datasource.local.model.CurrencyLocal
import com.project.segunfrancis.data.datasource.local.model.RatesLocal
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.model.RatesDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class CurrencyLocalMapper @Inject constructor() : Mapper<CurrencyLocal, CurrencyDomain> {
    override fun mapLocalToDomain(data: CurrencyLocal): CurrencyDomain {
        return with(data) {
            CurrencyDomain(base, date, with(rates) {
                this.map {
                    RatesDomain(it.currencyCode, it.exchangeRate)
                }
            }, success, timestamp)
        }
    }

    override fun mapDomainToLocal(data: CurrencyDomain): CurrencyLocal {
        return with(data) {
            CurrencyLocal(base, date, with(rates) {
                this.map {
                    RatesLocal(it.currencyCode, it.exchangeRate)
                }
            }, success, timestamp)
        }
    }
}