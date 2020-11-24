package com.project.segunfrancis.currencyconverter.mapper

import com.project.segunfrancis.currencyconverter.model.Currency
import com.project.segunfrancis.currencyconverter.model.Rates
import com.project.segunfrancis.domain.model.CurrencyDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class CurrencyMapper @Inject constructor() : Mapper<CurrencyDomain, Currency> {
    override fun mapDomainToApp(data: CurrencyDomain): Currency {
        return with(data) {
            Currency(base, date, with(rates) {
                this.map {
                    Rates(it.currencyCode, it.exchangeRate)
                }
            }, success, timestamp)
        }
    }
}