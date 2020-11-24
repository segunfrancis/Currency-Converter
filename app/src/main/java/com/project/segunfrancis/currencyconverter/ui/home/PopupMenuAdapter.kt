package com.project.segunfrancis.currencyconverter.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.emoji.widget.EmojiAppCompatTextView
import com.project.segunfrancis.currencyconverter.R
import com.project.segunfrancis.currencyconverter.model.Rates
import com.project.segunfrancis.currencyconverter.util.toFlagEmoji
import com.skydoves.powermenu.MenuBaseAdapter


/**
 * Created by SegunFrancis
 */

class PopupMenuAdapter : MenuBaseAdapter<Rates>() {

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View? {
        var mView = view
        val context = viewGroup.context
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            mView = inflater.inflate(R.layout.item_popup_menu, viewGroup, false)
        }
        val item: Rates = getItem(index) as Rates
        val flagText: EmojiAppCompatTextView = mView!!.findViewById(R.id.item_emoji_flag)
        val countryCodeText: TextView = mView.findViewById(R.id.item_country_code)
        flagText.text = item.currencyCode.toFlagEmoji()
        countryCodeText.text = item.currencyCode
        return mView
    }

}