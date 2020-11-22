package com.project.segunfrancis.currencyconverter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.segunfrancis.currencyconverter.databinding.FragmentHomeBinding
import com.project.segunfrancis.currencyconverter.model.Rates
import com.project.segunfrancis.currencyconverter.util.Result.*
import com.project.segunfrancis.currencyconverter.util.ThousandSeparatorTextWatcher
import com.project.segunfrancis.currencyconverter.util.ThousandSeparatorTextWatcher.Companion.getOriginalString
import com.project.segunfrancis.currencyconverter.util.disable
import com.project.segunfrancis.currencyconverter.util.enable
import com.project.segunfrancis.currencyconverter.util.toFlagEmoji
import com.skydoves.powermenu.CustomPowerMenu
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.OnMenuItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private var exchangeRate1 = 0.0
    private var exchangeRate2 = 0.0
    private lateinit var customMenu1: CustomPowerMenu.Builder<Rates, PopupMenuAdapter>
    private lateinit var customMenu2: CustomPowerMenu.Builder<Rates, PopupMenuAdapter>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.convertButton.disable()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val thousandSeparatorTextWatcher =
            ThousandSeparatorTextWatcher(binding.etAmount) { textChange ->
                if (textChange) {
                    binding.convertButton.enable()
                } else {
                    binding.convertButton.disable()
                }
            }
        binding.etAmount.addTextChangedListener(thousandSeparatorTextWatcher)
        customMenu1 =
            CustomPowerMenu.Builder<Rates, PopupMenuAdapter>(requireContext(), PopupMenuAdapter())
                .setOnMenuItemClickListener(onCustom1ClickListener)
                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                .setAutoDismiss(true)
        customMenu2 =
            CustomPowerMenu.Builder<Rates, PopupMenuAdapter>(requireContext(), PopupMenuAdapter())
                .setOnMenuItemClickListener(onCustom2ClickListener)
                .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT)
                .setAutoDismiss(true)

        viewModel.getCurrency.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Loading -> {
                }
                is Success -> {
                    val currencyRates = result.data.rates
                    currencyRates.forEach {
                        customMenu1.addItem(it)
                        customMenu2.addItem(it)
                    }
                    binding.countryItemLayout.setOnClickListener {
                        customMenu1.build().showAsAnchorLeftBottom(binding.emojiText1)
                    }
                    binding.countryItemLayout2.setOnClickListener {
                        customMenu2.build().showAsAnchorLeftBottom(binding.emojiText2)
                    }

                    // Setting default values
                    binding.emojiText1.text = currencyRates[0].currencyCode.toFlagEmoji()
                    binding.countryCodeText1.text = currencyRates[0].currencyCode
                    binding.etLabel1.text = currencyRates[0].currencyCode
                    exchangeRate1 = currencyRates[0].exchangeRate

                    binding.emojiText2.text = currencyRates[1].currencyCode.toFlagEmoji()
                    binding.countryCodeText2.text = currencyRates[1].currencyCode
                    binding.etLabel2.text = currencyRates[1].currencyCode
                    exchangeRate2 = currencyRates[1].exchangeRate
                }
                is Error -> {
                    Timber.d(result.error)
                }
            }
        }

        binding.convertButton.setOnClickListener {
            if (!binding.etAmount.text.isNullOrEmpty()) {
                val originalString = getOriginalString(binding.etAmount.text.toString())
                val amount = originalString.toDouble()
                Timber.d("Amount: $amount")
                Timber.d("Exchange Rate 1: $exchangeRate1")
                Timber.d("Exchange Rate 2: $exchangeRate2")
                val convertedAmount = (amount.times(exchangeRate2)).div(exchangeRate1)
                binding.etAmount2.setText(convertedAmount.toString())
            }
        }
    }

    private val onCustom1ClickListener =
        OnMenuItemClickListener<Rates> { _, item ->
            binding.emojiText1.text = item.currencyCode.toFlagEmoji()
            binding.countryCodeText1.text = item.currencyCode
            binding.etLabel1.text = item.currencyCode
            exchangeRate1 = item.exchangeRate
        }

    private val onCustom2ClickListener =
        OnMenuItemClickListener<Rates> { _, item ->
            binding.emojiText2.text = item.currencyCode.toFlagEmoji()
            binding.countryCodeText2.text = item.currencyCode
            binding.etLabel2.text = item.currencyCode
            exchangeRate2 = item.exchangeRate
        }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}