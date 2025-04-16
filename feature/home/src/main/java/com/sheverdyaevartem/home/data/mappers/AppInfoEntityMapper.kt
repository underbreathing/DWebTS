package com.sheverdyaevartem.home.data.mappers

import com.sheverdyaevartem.database.entity.AppInfoEntity
import com.sheverdyaevartem.home.di.FeatureHomeScope
import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import javax.inject.Inject

@FeatureHomeScope
class AppInfoEntityMapper @Inject constructor() {

    fun map(appInfoEntity: AppInfoEntity): AppMetaInfo {
        return with(appInfoEntity) { AppMetaInfo(appName, appIconUri, packageName) }
    }
}