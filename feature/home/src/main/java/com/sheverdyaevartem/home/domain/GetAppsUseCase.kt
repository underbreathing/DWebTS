package com.sheverdyaevartem.home.domain

import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(private val appsRepository: AppsRepository) {

    suspend operator fun invoke(): List<AppMetaInfo> {
        return appsRepository.getAppsMetaInf()
    }
}
