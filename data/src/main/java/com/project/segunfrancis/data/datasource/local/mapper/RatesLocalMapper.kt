package com.project.segunfrancis.data.datasource.local.mapper

import com.project.segunfrancis.data.datasource.local.model.RatesLocal
import com.project.segunfrancis.domain.model.RatesDomain
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class RatesLocalMapper @Inject constructor() : Mapper<RatesLocal, RatesDomain> {
    override fun mapLocalToDomain(data: RatesLocal): RatesDomain {
        return with(data) {
            RatesDomain(
                AED, AFN, ALL, AMD, EUR
            )
        }
    }

    override fun mapDomainToLocal(data: RatesDomain): RatesLocal {
        return with(data) {
            RatesLocal(
                AED,
                AFN,
                ALL,
                AMD,
                EUR
            )
        }
    }
}