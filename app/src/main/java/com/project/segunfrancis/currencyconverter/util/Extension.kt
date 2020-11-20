package com.project.segunfrancis.currencyconverter.util

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

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