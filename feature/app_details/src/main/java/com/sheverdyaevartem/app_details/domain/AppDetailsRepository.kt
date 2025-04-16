package com.sheverdyaevartem.app_details.domain

import com.sheverdyaevartem.app_details.domain.model.AppDetails

interface AppDetailsRepository {

    suspend fun getAppDetails(packageName: String): AppDetails
}