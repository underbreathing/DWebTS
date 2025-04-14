package com.sheverdyaevartem.app_details.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sheverdyaevartem.app_details.R
import com.sheverdyaevartem.app_details.databinding.FragmentDetailsBinding
import com.sheverdyaevartem.core.di.AppProvider
import com.sheverdyaevartem.core.ui.BaseInjectFragment
import java.io.FileInputStream
import java.security.MessageDigest

class AppDetailsFragment : BaseInjectFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var navController: NavController? = null

    override fun inject(appProvider: AppProvider) {

    }

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

        val packageName: String =
            requireArguments().getString(
                requireContext().getString(com.sheverdyaevartem.core.R.string.action_global_details_graph_key)
            ) ?: ""
        val pm = requireContext().packageManager
        val packageInfo = pm.getPackageInfo(packageName, 0)
        val appName = packageInfo.applicationInfo?.let { pm.getApplicationLabel(it).toString() }
        val versionName = packageInfo.versionName
        val apkPath = packageInfo.applicationInfo?.sourceDir
        val checksum = calculateSHA1(apkPath)
        with(binding) {
            tvDetailsLabel.text = appName
            tvDetailsVersion.text = versionName
            tvDetailsPackageName.text = packageName
            tvDetailsChecksum.text = checksum
            Glide.with(requireContext())
                .load(pm.getApplicationIcon(packageName))
                .transform(RoundedCorners(8))
                .into(ivAppIcon)
            toolbarBack.setOnClickListener {
                navController?.navigateUp()
            }
            bLaunch.setOnClickListener {
                val launchIntent = pm.getLaunchIntentForPackage(packageName)
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(context, "Невозможно запустить приложение", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun calculateSHA1(filePath: String?): String? {
        if (filePath == null) return null
        val buffer = ByteArray(1024 * 4)
        val digest = MessageDigest.getInstance("SHA-1")
        FileInputStream(filePath).use { fis ->
            var read = fis.read(buffer)
            while (read != -1) {
                digest.update(buffer, 0, read)
                read = fis.read(buffer)
            }
        }
        val hashBytes = digest.digest()
        return hashBytes.joinToString("") { "%02x".format(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        navController = null
    }


}