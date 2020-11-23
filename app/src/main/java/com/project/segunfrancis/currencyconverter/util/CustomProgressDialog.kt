package com.project.segunfrancis.currencyconverter.util

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.project.segunfrancis.currencyconverter.databinding.DialogCustomLayoutBinding
import com.project.segunfrancis.currencyconverter.util.AppConstants.ON_BOARDING_PREF_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by SegunFrancis
 */

@AndroidEntryPoint
class CustomProgressDialog : DialogFragment() {

    private var _binding: DialogCustomLayoutBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCustomLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            requireDialog().dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)

        val editor = preferences.edit()
        editor.putBoolean(ON_BOARDING_PREF_KEY, true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}