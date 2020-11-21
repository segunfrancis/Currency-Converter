package com.project.segunfrancis.currencyconverter.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.currencyconverter.BuildConfig
import com.project.segunfrancis.currencyconverter.model.Rates
import com.project.segunfrancis.currencyconverter.util.Result
import com.project.segunfrancis.currencyconverter.util.asLiveData
import com.project.segunfrancis.domain.usecase.GetCurrencyUseCase
import com.project.segunfrancis.domain.usecase.GetRatesUseCase
import com.project.segunfrancis.domain.usecase.InsertRatesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Created by SegunFrancis
 */

class HomeViewModel @ViewModelInject constructor(
    private val insertRatesUseCase: InsertRatesUseCase,
    private val getRatesUseCase: GetRatesUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _getCurrencyRemote = MutableLiveData<Result<List<Rates>>>()
    val getCurrency = _getCurrencyRemote.asLiveData()

    init {
        getCurrencyRemote()
    }

    private fun getCurrencyRemote() {
        viewModelScope.launch(dispatcher) {
            getCurrencyUseCase.execute(BuildConfig.API_KEY)
                .onStart {
                    _getCurrencyRemote.postValue(Result.Loading)
                }
                .catch {
                    _getCurrencyRemote.postValue(Result.Error(it))
                }
                .collect {
                    val items = JSONObject(it)
                    val response = items.get("rates").toString()

                    val rates = formatStringResponse(response)
                    _getCurrencyRemote.postValue(Result.Success(rates))
                }
        }
    }

    private fun setCurrencyToLocal(rates: List<Rates>) {
        viewModelScope.launch(dispatcher) {

        }
    }

    private fun getCurrencyFromLocal() {
        viewModelScope.launch(dispatcher) {

        }
    }

    private fun formatStringResponse(data: String): List<Rates> {
        val items = data.removePrefix("{").removeSuffix("}").split(",")
        val rates = mutableListOf<Rates>()
        items.forEach { item ->
            val name = item.subSequence(1, 4).toString()
            val exchanger = item.subSequence(6 until item.length).toString().toDouble()
            val rate = Rates(name, exchanger)
            rates.add(rate)
        }
        return rates
    }
}