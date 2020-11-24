package com.project.segunfrancis.data.datasource.remote.source

import com.project.segunfrancis.data.datasource.remote.api.CurrencyApi
import com.project.segunfrancis.data.datasource.remote.mapper.CurrencyRemoteMapper
import com.project.segunfrancis.data.datasource.remote.model.CurrencyResponse
import com.project.segunfrancis.data.datasource.remote.model.RatesResponse
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONObject
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

class RemoteRepositoryImpl @Inject constructor(
    private val currencyApi: CurrencyApi, private val currencyRemoteMapper: CurrencyRemoteMapper
) :
    RemoteRepository {
    override fun getCurrency(apiKey: String): Flow<CurrencyDomain> {
        return flow {
            val items = JSONObject(currencyApi.getCurrency(apiKey).string())
            val rates = items.get("rates").toString()
            val success = items.get("success").toString().toBoolean()
            val date = items.get("date").toString()
            val base = items.get("base").toString()
            val timestamp = items.get("timestamp").toString().toInt()
            val currencyResponse = CurrencyResponse(
                base = base,
                date = date,
                rates = formatRateResponse(rates),
                timestamp = timestamp,
                success = success
            )
            emit(currencyRemoteMapper.mapLocalToDomain(currencyResponse))
        }
    }

    private fun formatRateResponse(data: String): List<RatesResponse> {
        val items = data.removePrefix("{").removeSuffix("}").split(",")
        val rates = mutableListOf<RatesResponse>()
        items.forEach { item ->
            val name = item.subSequence(1, 4).toString()
            val exchanger = item.subSequence(6 until item.length).toString().toDouble()
            val rate = RatesResponse(name, exchanger)
            rates.add(rate)
        }
        return rates
    }
}