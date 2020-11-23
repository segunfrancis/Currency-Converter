package com.project.segunfrancis.currencyconverter.util

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import java.util.*

/**
 * Created by SegunFrancis
 */

fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

fun View.makeGone() {
    visibility = View.GONE
}

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.disable() {
    alpha = 0.5F
    isEnabled = false
}

fun View.enable() {
    alpha = 1F
    isEnabled = true
}

fun String.toFlagEmoji(): String {

    val countryCodeCaps = this.toUpperCase(Locale.getDefault())
    val firstLetter = Character.codePointAt(countryCodeCaps, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCodeCaps, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}

fun Any.containsNothing(): Boolean {
    return toString().isEmpty()
}

fun ImageView.circularProgress() {
    setImageDrawable(CircularProgressDrawable(context).apply {
        setColorSchemeColors(Color.rgb(32, 77, 211), Color.rgb(7, 38, 129), Color.rgb(32, 205, 152))
        strokeWidth = 10F
        centerRadius = 80F
        start()
    })
}