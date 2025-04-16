package com.sheverdyaevartem.database.di

import com.sheverdyaevartem.database.api.AppInfoRepository
import com.sheverdyaevartem.database.impl.AppInfoRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface AppInfoRepositoryModule {

    @Binds
    fun bindAppInfoRepository(appInfoRepositoryImpl: AppInfoRepositoryImpl): AppInfoRepository
}