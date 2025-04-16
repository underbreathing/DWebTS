package com.sheverdyaevartem.home.domain

import com.sheverdyaevartem.home.domain.model.AppMetaInfo

interface AppsRepository {

    suspend fun getAppsMetaInf(): List<AppMetaInfo>
}
