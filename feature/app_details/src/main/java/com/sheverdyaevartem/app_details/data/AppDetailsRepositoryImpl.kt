package com.sheverdyaevartem.app_details.data

import android.content.Context
import com.sheverdyaevartem.app_details.di.AppDetailsScope
import com.sheverdyaevartem.app_details.domain.AppDetailsRepository
import com.sheverdyaevartem.app_details.domain.model.AppDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.security.MessageDigest
import javax.inject.Inject

@AppDetailsScope
class AppDetailsRepositoryImpl @Inject constructor(private val context: Context) :
    AppDetailsRepository {

    override suspend fun getAppDetails(packageName: String): AppDetails {
        return withContext(Dispatchers.IO) {
            val pm = context.packageManager
            val packageInfo = pm.getPackageInfo(packageName, 0)
            val appInfo = packageInfo.applicationInfo

            AppDetails(
                appName = appInfo?.let { pm.getApplicationLabel(it).toString() } ?: "",
                packageName = packageName,
                versionName = packageInfo.versionName ?: "",
                checksum = calculateSHA1(packageInfo.applicationInfo?.sourceDir),
                iconUri = "android.resource://${packageName}/${appInfo?.icon}"
            )
        }
    }

    private suspend fun calculateSHA1(filePath: String?): String? {
        return withContext(Dispatchers.IO) {
            if (filePath == null) return@withContext null
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
            hashBytes.joinToString("") { "%02x".format(it) }
        }

    }
}