package com.project.segunfrancis.currencyconverter.ui.home

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.project.segunfrancis.currencyconverter.BuildConfig
import com.project.segunfrancis.currencyconverter.MainCoroutineRule
import com.project.segunfrancis.currencyconverter.mapper.CurrencyMapper
import com.project.segunfrancis.currencyconverter.model.Currency
import com.project.segunfrancis.currencyconverter.util.AppConstants
import com.project.segunfrancis.currencyconverter.util.Event
import com.project.segunfrancis.currencyconverter.util.Result
import com.project.segunfrancis.data.datasource.local.db.CurrencyDatabase
import com.project.segunfrancis.data.datasource.local.mapper.CurrencyLocalMapper
import com.project.segunfrancis.data.datasource.local.source.LocalRepositoryImpl
import com.project.segunfrancis.data.datasource.remote.api.CurrencyApi
import com.project.segunfrancis.data.datasource.remote.mapper.CurrencyRemoteMapper
import com.project.segunfrancis.data.datasource.remote.source.RemoteRepositoryImpl
import com.project.segunfrancis.domain.model.CurrencyDomain
import com.project.segunfrancis.domain.model.RatesDomain
import com.project.segunfrancis.domain.repository.LocalRepository
import com.project.segunfrancis.domain.repository.RemoteRepository
import com.project.segunfrancis.domain.usecase.GetCurrencyLocalUseCase
import com.project.segunfrancis.domain.usecase.GetCurrencyRemoteUseCase
import com.project.segunfrancis.domain.usecase.InsertCurrencyUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by SegunFrancis
 */

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private fun provideDataBase(context: Context): CurrencyDatabase {
        return Room.inMemoryDatabaseBuilder(
            context.applicationContext,
            CurrencyDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }

    private fun provideApi(): CurrencyApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }

    private val currencyLocalMapper: CurrencyLocalMapper = CurrencyLocalMapper()
    private val currencyRemoteMapper: CurrencyRemoteMapper = CurrencyRemoteMapper()

    private val localRepository: LocalRepository = LocalRepositoryImpl(
        provideDataBase(InstrumentationRegistry.getInstrumentation().targetContext),
        currencyLocalMapper
    )

    private val remoteRepository: RemoteRepository =
        RemoteRepositoryImpl(provideApi(), currencyRemoteMapper)

    private val coroutineDispatcher: CoroutineDispatcher = coroutineRule.testDispatcher

    private val insertCurrencyUseCase: InsertCurrencyUseCase =
        InsertCurrencyUseCase(localRepository, coroutineDispatcher)

    private val getCurrencyLocalUseCase: GetCurrencyLocalUseCase =
        GetCurrencyLocalUseCase(localRepository, coroutineDispatcher)

    private val getCurrencyRemoteUseCase: GetCurrencyRemoteUseCase =
        GetCurrencyRemoteUseCase(remoteRepository, coroutineDispatcher)

    private val currencyMapper: CurrencyMapper = CurrencyMapper()

    private val apiKey: String = BuildConfig.API_KEY

    private lateinit var viewModel: HomeViewModel

    private val currency = CurrencyDomain(
        "EUR", "2020-10-12", listOf(RatesDomain("NGN", 512.0)),
        true, 143988473
    )

    @Before
    fun setUp() {
        viewModel = HomeViewModel(
            insertCurrencyUseCase,
            getCurrencyLocalUseCase,
            getCurrencyRemoteUseCase,
            currencyMapper,
            apiKey,
            coroutineDispatcher
        )
        viewModel.setCurrencyToLocal(currency)
    }

    @Test
    fun testToGetCurrencyFromLocalDatabase_successfullySetCurrencyToLocal() {
        viewModel.getCurrencyFromLocal()
        val item: Event<Result<Currency>> = viewModel.getCurrency.getOrAwaitValue()
        assert(item.peekContent() is Result.Success)
    }

    @Test
    fun testToGetCurrencyFromLocalDatabase_ratesIsNotEmpty() {
        viewModel.getCurrencyFromLocal()
        val item: Event<Result<Currency>> = viewModel.getCurrency.getOrAwaitValue()
        assertEquals(
            (item.peekContent() as Result.Success<Currency>).data?.rates?.isNotEmpty(),
            true
        )
    }
}