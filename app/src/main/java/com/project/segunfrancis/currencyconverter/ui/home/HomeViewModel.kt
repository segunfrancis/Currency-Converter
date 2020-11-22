package com.project.segunfrancis.currencyconverter.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.currencyconverter.BuildConfig
import com.project.segunfrancis.currencyconverter.mapper.CurrencyMapper
import com.project.segunfrancis.currencyconverter.model.Currency
import com.project.segunfrancis.currencyconverter.model.Rates
import com.project.segunfrancis.currencyconverter.util.Result
import com.project.segunfrancis.currencyconverter.util.asLiveData
import com.project.segunfrancis.domain.usecase.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created by SegunFrancis
 */

class HomeViewModel @ViewModelInject constructor(
    private val insertRatesUseCase: InsertCurrencyUseCase,
    private val getCurrencyLocalUseCase: GetCurrencyLocalUseCase,
    private val getCurrencyRemoteUseCase: GetCurrencyRemoteUseCase,
    private val currencyMapper: CurrencyMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _getCurrencyRemote = MutableLiveData<Result<Currency>>()
    val getCurrency = _getCurrencyRemote.asLiveData()

    init {
        getCurrencyRemote()
    }

    private fun getCurrencyRemote() {
        viewModelScope.launch(dispatcher) {
            getCurrencyRemoteUseCase.execute(BuildConfig.API_KEY)
                .onStart {
                    _getCurrencyRemote.postValue(Result.Loading)
                }
                .catch {
                    _getCurrencyRemote.postValue(Result.Error(it))
                }
                .collect {
                    Timber.d(it.toString())
                    _getCurrencyRemote.postValue(Result.Success(currencyMapper.mapDomainToApp(it)))
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
}