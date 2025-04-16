package com.sheverdyaevartem.database.impl

import com.sheverdyaevartem.database.AppInfoDb
import com.sheverdyaevartem.database.api.AppInfoRepository
import com.sheverdyaevartem.database.entity.AppInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppInfoRepositoryImpl @Inject constructor(database: AppInfoDb) :
    AppInfoRepository {

    private val appInfoDao = database.getAppInfoDao()

    override fun getAppsInfo(): Flow<List<AppInfoEntity>> {
        return appInfoDao.getAppsInfo()
    }

    override suspend fun clearAppsInfo() {
        appInfoDao.clearAppsInfo()
    }

//    override suspend fun deleteAppInfo(packageName: String) {
//        appInfoDao.deleteAppInfo(packageName)
//    }

    override suspend fun insertAppInfo(appInfoEntity: AppInfoEntity) {
        appInfoDao.insertAppInfo(appInfoEntity)
    }

    override suspend fun insertAppInfoList(appInfoEntities: List<AppInfoEntity>) {
        return appInfoDao.insertAppInfoList(appInfoEntities)
    }
}