package com.sheverdyaevartem.app_details.di

import com.sheverdyaevartem.app_details.data.AppDetailsRepositoryImpl
import com.sheverdyaevartem.app_details.domain.AppDetailsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindAppDetailsRepository(appDetailsRepositoryImpl: AppDetailsRepositoryImpl): AppDetailsRepository
}