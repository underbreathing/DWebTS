package com.sheverdyaevartem.home.domain

import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import kotlinx.coroutines.flow.Flow

interface AppsCacheRepository {

    fun getAppMetas(): Flow<List<AppMetaInfo>>

    suspend fun updateAppsInfo(appsInfoList: List<AppMetaInfo>)
}
