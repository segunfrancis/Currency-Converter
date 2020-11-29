package com.project.segunfrancis.data.datasource.remote.source

import com.project.segunfrancis.data.datasource.remote.api.CurrencyApi
import com.project.segunfrancis.data.datasource.remote.mapper.CurrencyRemoteMapper
import junit.framework.TestCase.assertNotNull
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by SegunFrancis
 */

class RemoteRepositoryImplTest {

    private val remoteMapper: CurrencyRemoteMapper = CurrencyRemoteMapper()

    private val currencyApi: CurrencyApi = Retrofit.Builder()
        .baseUrl("http://data.fixer.io/api/")
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(CurrencyApi::class.java)

    lateinit var remoteRepositoryImpl: RemoteRepositoryImpl

    private val rates: String = "{\"AED\":4.394335,\"AFN\":92.121316,\"ALL\":124.004751,\"AMD\":608.010219," +
            "\"PHP\":57.575593,\"PKR\":190.762441,\"PLN\":4.483595,\"PYG\":8431.949752,\"QAR\":4.355703," +
            "\"RON\":4.873558,\"RSD\":117.549871,\"RUB\":91.018898,\"RWF\":1182.616684,\"SAR\":4.486838," +
            "\"SBD\":9.573451,\"SCR\":24.862,\"SDG\":66.163938,\"SEK\":10.162732,\"SGD\":1.600994,\"SHP\":0.898549," +
            "\"SLL\":11978.709043,\"SOS\":697.487028,\"SRD\":16.933537,\"STD\":25162.046488,\"SVC\":10.46857," +
            "\"SYP\":613.663884,\"SZL\":18.257125,\"THB\":36.270546,\"TJS\":13.551645,\"TMT\":4.187312," +
            "\"TND\":3.277474,\"TOP\":2.739041,\"TRY\":9.373842,\"TTD\":8.116578,\"TWD\":34.118825,\"TZS\":2774.326665," +
            "\"UAH\":34.10478,\"UGX\":4426.439986,\"USD\":1.196375,\"UYU\":50.982225,\"UZS\":12460.24599,\"VEF\":11.9488," +
            "\"VND\":27709.839483,\"VUV\":132.210951,\"WST\":3.099439,\"XAF\":658.193384,\"XAG\":0.052713,\"XAU\":0.00067," +
            "\"XCD\":3.233264,\"XDR\":0.839352,\"XOF\":659.203021,\"XPF\":119.937029,\"YER\":299.456966,\"ZAR\":18.264447," +
            "\"ZMK\":10768.814918,\"ZMW\":25.138214,\"ZWL\":385.232678}"


    @Before
    fun init() {
        remoteRepositoryImpl = RemoteRepositoryImpl(currencyApi, remoteMapper)
    }

    @Test
    fun testFormatRateResponseMethod() {
        val data = remoteRepositoryImpl.formatRateResponse(rates)
        assertNotNull(data)
    }
}