package com.sheverdyaevartem.home.data.mappers

import com.sheverdyaevartem.database.entity.AppInfoEntity
import com.sheverdyaevartem.home.di.FeatureHomeScope
import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import javax.inject.Inject

@FeatureHomeScope
class AppInfoMapper @Inject constructor() {

    fun map(appMetaInfo: AppMetaInfo): AppInfoEntity {
        return with(appMetaInfo) { AppInfoEntity(packageName, appName, appIconUri) }
    }
}