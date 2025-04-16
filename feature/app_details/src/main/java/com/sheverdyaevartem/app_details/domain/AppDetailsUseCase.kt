package com.sheverdyaevartem.app_details.domain

import com.sheverdyaevartem.app_details.di.AppDetailsScope
import com.sheverdyaevartem.app_details.domain.model.AppDetails
import javax.inject.Inject

@AppDetailsScope
class AppDetailsUseCase @Inject constructor(private val detailsRepository: AppDetailsRepository) {

    suspend operator fun invoke(packageName: String): AppDetails {
        return detailsRepository.getAppDetails(packageName)
    }
}