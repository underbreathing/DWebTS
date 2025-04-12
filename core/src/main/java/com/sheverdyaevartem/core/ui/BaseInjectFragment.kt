package com.sheverdyaevartem.core.ui

import android.content.Context
import androidx.fragment.app.Fragment
import com.sheverdyaevartem.core.di.App
import com.sheverdyaevartem.core.di.AppProvider

abstract class BaseInjectFragment : Fragment() {

    override fun onAttach(context: Context) {
        inject((requireActivity().application as App).getAppProvider())
        super.onAttach(context)
    }

    abstract fun inject(appProvider: AppProvider)

}