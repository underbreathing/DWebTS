package com.sheverdyaevartem.app_details.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sheverdyaevartem.app_details.R
import com.sheverdyaevartem.app_details.databinding.FragmentDetailsBinding
import com.sheverdyaevartem.app_details.di.AppDetailsComponent
import com.sheverdyaevartem.app_details.domain.model.AppDetails
import com.sheverdyaevartem.app_details.ui.state.DetailsState
import com.sheverdyaevartem.core.di.AndroidDepsProvider
import com.sheverdyaevartem.core.ui.BaseInjectFragment
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class AppDetailsFragment : BaseInjectFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null

    override fun inject(androidDepsProvider: AndroidDepsProvider) {
        AppDetailsComponent.init(androidDepsProvider).inject(this)
    }

    private val homeViewModel: AppDetailsViewModel by viewModels {
        homeViewModelFactory.get().create(
            requireArguments().getString(
                requireContext().getString(com.sheverdyaevartem.core.R.string.action_global_details_graph_key)
            ) ?: ""
        )
    }

    @Inject
    internal lateinit var homeViewModelFactory: Provider<AppDetailsViewModel.AppDetailsViewModelFactory.Factory>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navController = findNavController()
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.detailsState.collect { detailsState: DetailsState ->
                    when (detailsState) {
                        is DetailsState.Content -> renderDetails(detailsState.appDetails)
                        DetailsState.Loading -> {}
                    }
                }
            }
        }
    }

    private fun renderDetails(details: AppDetails) {
        with(binding) {
            tvDetailsLabel.text = details.appName
            tvDetailsVersion.text = details.versionName
            tvDetailsPackageName.text = details.packageName
            tvDetailsChecksum.text = details.checksum
            Glide.with(requireContext())
                .load(Uri.parse(details.iconUri))
                .placeholder(com.sheverdyaevartem.core.R.drawable.ic_app_placeholder)
                .transform(RoundedCorners(8))
                .into(ivAppIcon)
            toolbarBack.setOnClickListener {
                navController?.navigateUp()
            }
            bLaunch.setOnClickListener {
                val launchIntent =
                    requireContext().packageManager.getLaunchIntentForPackage(details.packageName)
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(
                        context,
                        getString(R.string.details_launch_error), Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController = null
    }


}