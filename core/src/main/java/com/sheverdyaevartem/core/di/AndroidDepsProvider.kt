package com.sheverdyaevartem.core.di

import android.app.Application
import android.content.Context

interface AndroidDepsProvider{
    fun provideContext(): Context

    fun provideApplication(): Application
}
