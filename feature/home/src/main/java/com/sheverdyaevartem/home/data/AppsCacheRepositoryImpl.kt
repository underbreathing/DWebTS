package com.sheverdyaevartem.home.data

import com.sheverdyaevartem.database.api.AppInfoRepository
import com.sheverdyaevartem.home.data.mappers.AppInfoEntityMapper
import com.sheverdyaevartem.home.data.mappers.AppInfoMapper
import com.sheverdyaevartem.home.domain.AppsCacheRepository
import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppsCacheRepositoryImpl @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
    private val appInfoEntityMapper: AppInfoEntityMapper,
    private val appInfoMapper: AppInfoMapper,
) :
    AppsCacheRepository {
    override fun getAppMetas(): Flow<List<AppMetaInfo>> {
        return appInfoRepository.getAppsInfo()
            .map { metaInfoList -> metaInfoList.map(appInfoEntityMapper::map) }
    }

    override suspend fun updateAppsInfo(appsInfoList: List<AppMetaInfo>) {
        appInfoRepository.clearAppsInfo()
        appInfoRepository.insertAppInfoList(appsInfoList.map(appInfoMapper::map))
    }
}