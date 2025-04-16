package com.sheverdyaevartem.home.domain

import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppsCacheInteractor @Inject constructor(private val appsCacheRepository: AppsCacheRepository) {

    fun getAppsInfoCache(): Flow<List<AppMetaInfo>> {
        return appsCacheRepository.getAppMetas()
    }

    suspend fun updateAppsInfo(appsInfoList: List<AppMetaInfo>) {
        appsCacheRepository.updateAppsInfo(appsInfoList)
    }
}
