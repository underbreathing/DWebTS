package com.sheverdyaevartem.home.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.sheverdyaevartem.core.di.AndroidDepsProvider
import com.sheverdyaevartem.core.ui.BaseInjectFragment
import com.sheverdyaevartem.core.utils.log
import com.sheverdyaevartem.home.databinding.FragmentHomeBinding
import com.sheverdyaevartem.core.R
import com.sheverdyaevartem.home.di.FeatureHomeComponent
import com.sheverdyaevartem.home.presentation.rv.AppAdapter
import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import com.sheverdyaevartem.home.presentation.state.HomeState
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


class HomeFragment : BaseInjectFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var appAdapter = AppAdapter { app: AppMetaInfo ->
        findNavController().navigate(
            R.id.action_global_details_graph,
            bundleOf(
                requireContext().getString(R.string.action_global_details_graph_key)
                        to app.packageName
            )
        )
    }

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory.get()
    }

    @Inject
    lateinit var homeViewModelFactory: Provider<HomeViewModel.HomeViewModelFactory>

    private val receiver: ReceiverAppUpdates = ReceiverAppUpdates()


    override fun inject(androidDepsProvider: AndroidDepsProvider) {
        FeatureHomeComponent.init(androidDepsProvider).inject(this)
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
            ReceiverAppUpdates.updatesFlow.collect {
                "Надо перерисовывать список!!!!!!!!!!!!!!!!!!!".log()
            }
        }

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.homeState.collect { state: HomeState ->
                    when (state) {
                        is HomeState.Content -> showContent(state.content)
                        HomeState.Loading -> showLoading()
                    }
                }
            }
        }

        binding.rvApps.adapter = appAdapter
    }

    private fun showLoading() {

    }

    private fun showContent(content: List<AppMetaInfo>) {
        appAdapter.submitList(content)
    }

    override fun onDestroyView() {
        requireContext().unregisterReceiver(receiver)
        _binding = null
        super.onDestroyView()
    }
}