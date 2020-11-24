package com.project.segunfrancis.currencyconverter.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.segunfrancis.currencyconverter.BuildConfig
import com.project.segunfrancis.currencyconverter.mapper.CurrencyMapper
import com.project.segunfrancis.currencyconverter.model.Currency
import com.project.segunfrancis.currencyconverter.util.Event
import com.project.segunfrancis.currencyconverter.util.Result
import com.project.segunfrancis.currencyconverter.util.asLiveData
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.usecase.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * Created by SegunFrancis
 */

class HomeViewModel @ViewModelInject constructor(
    private val insertCurrencyUseCase: InsertCurrencyUseCase,
    private val getCurrencyLocalUseCase: GetCurrencyLocalUseCase,
    private val getCurrencyRemoteUseCase: GetCurrencyRemoteUseCase,
    private val currencyMapper: CurrencyMapper,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _getCurrencyRemote = MutableLiveData<Event<Result<Currency>>>()
    val getCurrency = _getCurrencyRemote.asLiveData()

    init {
        getCurrencyFromLocal()
        getCurrencyRemote()
    }

    fun getCurrencyRemote() {
        viewModelScope.launch(dispatcher) {
            getCurrencyRemoteUseCase.execute(BuildConfig.API_KEY)
                .onStart {
                    _getCurrencyRemote.postValue(Event(Result.Loading))
                }
                .catch {
                    _getCurrencyRemote.postValue(Event(Result.NetworkError(it)))
                }
                .collect {
                    setCurrencyToLocal(it)
                }
        }
    }

    private fun setCurrencyToLocal(currency: CurrencyDomain) {
        viewModelScope.launch(dispatcher) {
            insertCurrencyUseCase.execute(currency)
                .onCompletion { getCurrencyFromLocal() }
                .collect { }
        }
    }

    private fun getCurrencyFromLocal() {
        viewModelScope.launch(dispatcher) {
            getCurrencyLocalUseCase.execute()
                .catch {
                    _getCurrencyRemote.postValue(Event(Result.DatabaseError(it)))
                }
                .collect {
                    _getCurrencyRemote.postValue(Event(Result.Success(currencyMapper.mapDomainToApp(it))))
                }
        }
    }
}