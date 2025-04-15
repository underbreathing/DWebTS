package com.sheverdyaevartem.home.ui

import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sheverdyaevartem.core.di.AppProvider
import com.sheverdyaevartem.core.ui.BaseInjectFragment
import com.sheverdyaevartem.core.utils.log
import com.sheverdyaevartem.home.databinding.FragmentHomeBinding
import com.sheverdyaevartem.core.R
import com.sheverdyaevartem.home.ui.rv.AppAdapter
import com.sheverdyaevartem.home.ui.rv.AppMetaInfo
import kotlinx.coroutines.launch


class HomeFragment : BaseInjectFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var appAdapter = AppAdapter { app: AppMetaInfo ->

        val key = requireContext().getString(R.string.action_global_details_graph_key)
        val packagename = app.packageName

        findNavController().navigate(
            R.id.action_global_details_graph,
            bundleOf(key to app.packageName)
        )
    }

    private val receiver: ReceiverAppUpdates = ReceiverAppUpdates()


    override fun inject(appProvider: AppProvider) {
        // FeatureHomeComponent.init(appProvider).inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filter = IntentFilter().apply {
            addAction(Intent.ACTION_PACKAGE_ADDED)
            addAction(Intent.ACTION_PACKAGE_REPLACED)
            addAction(Intent.ACTION_PACKAGE_REMOVED)
            addDataScheme("package")
        }
        requireContext().registerReceiver(receiver, filter)

        lifecycleScope.launch {
            ReceiverAppUpdates.updatesFlow.collect{
                "Надо перерисовывать список!!!!!!!!!!!!!!!!!!!".log()
            }
        }

        val packageManager = context?.packageManager
        val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)

        val listOfApps = mutableListOf<AppMetaInfo>()

        if (apps != null) {
            apps.filter { app ->
                app.flags and ApplicationInfo.FLAG_SYSTEM == 0
            }.forEach { app ->
                val appName = packageManager.getApplicationLabel(app).toString()
                val packageName = app.packageName
                "App: $appName, Package: $packageName , System: ${app.flags and ApplicationInfo.FLAG_SYSTEM != 0} ".log()
                listOfApps.add(
                    AppMetaInfo(
                        appName,
                        packageManager.getApplicationIcon(app),
                        packageName
                    )
                )
            }
        }

        with(binding) {
            rvApps.adapter = appAdapter
            appAdapter.setNewItems(listOfApps)
            appAdapter.notifyDataSetChanged()
        }

    }

    override fun onDestroyView() {
        requireContext().unregisterReceiver(receiver)
        _binding = null
        super.onDestroyView()
    }
}