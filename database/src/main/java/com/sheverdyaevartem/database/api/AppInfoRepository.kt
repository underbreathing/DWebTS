package com.sheverdyaevartem.database.api

import com.sheverdyaevartem.database.entity.AppInfoEntity
import kotlinx.coroutines.flow.Flow

interface AppInfoRepository {

    fun getAppsInfo(): Flow<List<AppInfoEntity>>

    //suspend fun deleteAppInfo(packageName: String)

    suspend fun clearAppsInfo()

    suspend fun insertAppInfo(appInfoEntity: AppInfoEntity)

    suspend fun insertAppInfoList(appInfoEntities: List<AppInfoEntity>)

}