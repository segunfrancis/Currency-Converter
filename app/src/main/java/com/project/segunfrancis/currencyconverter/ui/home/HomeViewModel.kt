package com.project.segunfrancis.currencyconverter.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.project.segunfrancis.domain.usecase.GetCurrencyUseCase
import com.project.segunfrancis.domain.usecase.GetRatesUseCase
import com.project.segunfrancis.domain.usecase.InsertRatesUseCase

/**
 * Created by SegunFrancis
 */

class HomeViewModel @ViewModelInject constructor(
    private val insertRatesUseCase: InsertRatesUseCase,
    private val getRatesUseCase: GetRatesUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase
) : ViewModel() {


}