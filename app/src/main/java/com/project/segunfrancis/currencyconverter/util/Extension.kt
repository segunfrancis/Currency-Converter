package com.project.segunfrancis.currencyconverter.util

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.currencyconverter.R
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
    alpha = 0.6F
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

fun View.showMessage(message: String, error: Boolean) {
    if (error) {
        val snack = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        snack.setBackgroundTint(ContextCompat.getColor(context, R.color.custom_red))
        snack.show()
    } else { // Success
        val snack = Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
        snack.setBackgroundTint(ContextCompat.getColor(context, R.color.custom_green))
        snack.show()
    }
}