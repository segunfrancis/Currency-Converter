package com.project.segunfrancis.currencyconverter

import android.app.Application
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by SegunFrancis
 */

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        val config: EmojiCompat.Config
        val fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
            .setReplaceAll(true)
            .registerInitCallback(object : EmojiCompat.InitCallback() {
                override fun onInitialized() {
                    Timber.d("EmojiCompat initialized")
                }

                override fun onFailed(throwable: Throwable?) {
                    Timber.e(throwable)
                }
            })
        EmojiCompat.init(config)
    }
}