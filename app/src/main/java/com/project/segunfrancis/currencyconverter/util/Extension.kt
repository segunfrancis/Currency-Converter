package com.project.segunfrancis.currencyconverter.util

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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