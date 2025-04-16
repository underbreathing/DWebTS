package com.sheverdyaevartem.home.data

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.sheverdyaevartem.core.utils.log
import com.sheverdyaevartem.home.domain.AppsRepository
import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(private val context: Context) : AppsRepository {
    override suspend fun getAppsMetaInf(): List<AppMetaInfo> {
        val packageManager = context.packageManager
        val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
        val listOfApps = mutableListOf<AppMetaInfo>()
        if (apps != null) {
            apps.filter { app ->
                app.flags and ApplicationInfo.FLAG_SYSTEM == 0
            }.forEach { app ->
                val appName = packageManager.getApplicationLabel(app).toString()
                val packageName = app.packageName
                "App: $appName, Package: $packageName , System: ${app.flags and ApplicationInfo.FLAG_SYSTEM != 0} ".log()
                val iconResId = app.icon
                listOfApps.add(
                    AppMetaInfo(
                        appName,
                        "android.resource://${app.packageName}/$iconResId",
                        packageName
                    )
                )
            }
        }
        return listOfApps
    }
}