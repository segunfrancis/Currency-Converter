package com.project.segunfrancis.currencyconverter.ui.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.segunfrancis.currencyconverter.databinding.FragmentHomeBinding
import com.project.segunfrancis.currencyconverter.model.Rates
import com.project.segunfrancis.currencyconverter.util.*
import com.project.segunfrancis.currencyconverter.util.AppConstants.CUSTOM_PROGRESS_DIALOG_TAG
import com.project.segunfrancis.currencyconverter.util.AppConstants.ON_BOARDING_PREF_KEY
import com.project.segunfrancis.currencyconverter.util.Result.*
import com.project.segunfrancis.currencyconverter.util.ThousandSeparatorTextWatcher.Companion.getOriginalString
import com.skydoves.powermenu.CustomPowerMenu
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.OnMenuItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.text.DecimalFormat
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var preferences: SharedPreferences
    private var exchangeRate1 = 0.0
    private var exchangeRate2 = 0.0
    private var currencyCode1 = ""
    private var currencyCode2 = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!preferences.getBoolean(ON_BOARDING_PREF_KEY, false))
            CustomProgressDialog().show(childFragmentManager, CUSTOM_PROGRESS_DIALOG_TAG)

        binding.convertButton.disable()

        val thousandSeparatorTextWatcher =
            ThousandSeparatorTextWatcher(binding.etAmount) { textChange ->
                if (textChange) {
                    binding.convertButton.enable()
                } else {
                    binding.convertButton.disable()
                }
            }
        binding.etAmount.addTextChangedListener(thousandSeparatorTextWatcher)

        viewModel.getCurrency.observe(viewLifecycleOwner, EventObserver { result ->
            when (result) {
                is Loading -> {
                    binding.swipeRefresh.setRefreshing(true)
                }
                is Success -> {
                    binding.swipeRefresh.setRefreshing(false)
                    requireView().showMessage("Success", false)
                    val currencyRates = result.data.rates
                    binding.countryItemLayout.setOnClickListener { view ->
                        val customMenu1: CustomPowerMenu.Builder<Rates, PopupMenuAdapter> =
                            CustomPowerMenu.Builder<Rates, PopupMenuAdapter>(
                                requireContext(),
                                PopupMenuAdapter()
                            )
                                .setOnMenuItemClickListener(onCustom1ClickListener)
                                .setAnimation(MenuAnimation.SHOWUP_TOP_LEFT)
                                .setAutoDismiss(true)
                        currencyRates.forEach { customMenu1.addItem(it) }
                        customMenu1.build().showAsAnchorLeftBottom(view)
                    }
                    binding.countryItemLayout2.setOnClickListener { view ->
                        val customMenu2: CustomPowerMenu.Builder<Rates, PopupMenuAdapter> =
                            CustomPowerMenu.Builder<Rates, PopupMenuAdapter>(
                                requireContext(),
                                PopupMenuAdapter()
                            )
                                .setOnMenuItemClickListener(onCustom2ClickListener)
                                .setAnimation(MenuAnimation.SHOWUP_TOP_RIGHT)
                                .setAutoDismiss(true)
                        currencyRates.forEach { customMenu2.addItem(it) }
                        customMenu2.build().showAsAnchorLeftBottom(view)
                    }

                    if (binding.emojiText1.text.isNullOrEmpty()) {
                        // Setting default values
                        currencyCode1 = currencyRates[0].currencyCode
                        binding.emojiText1.text = currencyCode1.toFlagEmoji()
                        binding.countryCodeText1.text = currencyCode1
                        binding.etLabel1.text = currencyCode1
                        exchangeRate1 = currencyRates[0].exchangeRate

                        currencyCode2 = currencyRates[1].currencyCode
                        binding.emojiText2.text = currencyCode2.toFlagEmoji()
                        binding.countryCodeText2.text = currencyCode2
                        binding.etLabel2.text = currencyCode2
                        exchangeRate2 = currencyRates[1].exchangeRate
                    }
                }
                is NetworkError -> {
                    binding.swipeRefresh.setRefreshing(false)
                    requireView().showMessage(result.errorMessage, true)
                    Timber.e(result.error)
                }
                is DatabaseError -> {
                    binding.swipeRefresh.setRefreshing(false)
                    requireView().showMessage(result.errorMessage, true)
                    Timber.e(result.error)
                }
            }

            binding.swipeRefresh.setRefreshListener {
                viewModel.getCurrencyRemote()
            }
        })

        binding.convertButton.setOnClickListener {
            if (!binding.etAmount.text.isNullOrEmpty()) {
                val originalString = getOriginalString(binding.etAmount.text.toString())
                val amount = originalString.toDouble()
                val convertedAmount = (amount.times(exchangeRate2)).div(exchangeRate1)
                val decimalFormat = DecimalFormat("#,###.##")
                binding.etAmount2.setText(decimalFormat.format(convertedAmount))
            }
        }
    }

    private val onCustom1ClickListener =
        OnMenuItemClickListener<Rates> { _, item ->
            binding.emojiText1.text = item.currencyCode.toFlagEmoji()
            binding.countryCodeText1.text = item.currencyCode
            binding.etLabel1.text = item.currencyCode
            exchangeRate1 = item.exchangeRate
            currencyCode1 = item.currencyCode
        }

    private val onCustom2ClickListener =
        OnMenuItemClickListener<Rates> { _, item ->
            binding.emojiText2.text = item.currencyCode.toFlagEmoji()
            binding.countryCodeText2.text = item.currencyCode
            binding.etLabel2.text = item.currencyCode
            exchangeRate2 = item.exchangeRate
            currencyCode2 = item.currencyCode
        }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}