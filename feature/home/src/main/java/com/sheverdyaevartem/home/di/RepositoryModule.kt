package com.sheverdyaevartem.home.di

import com.sheverdyaevartem.home.data.AppsCacheRepositoryImpl
import com.sheverdyaevartem.home.data.AppsRepositoryImpl
import com.sheverdyaevartem.home.domain.AppsCacheRepository
import com.sheverdyaevartem.home.domain.AppsRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindAppsRepository(appsRepositoryImpl: AppsRepositoryImpl): AppsRepository

    @Binds
    fun bindAppsCacheRepository(appsCacheRepositoryImpl: AppsCacheRepositoryImpl): AppsCacheRepository
}