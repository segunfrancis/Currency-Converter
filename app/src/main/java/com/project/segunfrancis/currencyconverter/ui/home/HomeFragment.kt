package com.project.segunfrancis.currencyconverter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.segunfrancis.currencyconverter.databinding.FragmentHomeBinding
import com.project.segunfrancis.currencyconverter.util.Result.*
import com.project.segunfrancis.currencyconverter.util.ThousandSeparatorTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val thousandSeparatorTextWatcher = ThousandSeparatorTextWatcher(binding.etAmount)
        binding.etAmount.addTextChangedListener(thousandSeparatorTextWatcher)
        viewModel.getCurrency.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Loading -> {
                }
                is Success -> {
                    Timber.d(result.data.date)
                }
                is Error -> {
                    Timber.d(result.error)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}