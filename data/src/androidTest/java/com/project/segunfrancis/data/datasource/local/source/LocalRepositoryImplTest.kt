package com.project.segunfrancis.data.datasource.local.source

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.project.segunfrancis.data.MainCoroutineRule
import com.project.segunfrancis.data.datasource.local.db.CurrencyDatabase
import com.project.segunfrancis.data.datasource.local.model.CurrencyLocal
import com.project.segunfrancis.data.datasource.local.model.RatesLocal
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by SegunFrancis
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class LocalRepositoryImplTest {

    @get:Rule
    val coroutineRule: MainCoroutineRule = MainCoroutineRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val db: CurrencyDatabase =
        Room.inMemoryDatabaseBuilder(context.applicationContext, CurrencyDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val currency = CurrencyLocal(
        "EUR", "2020-10-12", listOf(RatesLocal("NGN", 512.0)),
        true, 143988473
    )

    @Test
    fun testReadingAndWritingToDatabase() {
        runBlocking(coroutineRule.testDispatcher) {
            db.currencyDao().insertRates(currency)
            val currency = db.currencyDao().getAllRates()
            assertNotNull(currency)
            assertEquals(currency.rates.isNotEmpty(), true)
        }
    }

    @After
    fun cleanUp() {
        db.close()
    }
}