package com.sheverdyaevartem.app_details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheverdyaevartem.app_details.databinding.FragmentDetailsBinding
import com.sheverdyaevartem.core.di.AppProvider
import com.sheverdyaevartem.core.ui.BaseInjectFragment

class AppDetailsFragment: BaseInjectFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun inject(appProvider: AppProvider) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }


}