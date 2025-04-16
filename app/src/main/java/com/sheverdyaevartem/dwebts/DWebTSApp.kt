package com.sheverdyaevartem.dwebts

import android.app.Application
import com.sheverdyaevartem.core.di.App
import com.sheverdyaevartem.core.di.AndroidDepsProvider
import com.sheverdyaevartem.daggerpractic.di.AppComponent

class DWebTSApp : Application(), App {

    private lateinit var appComponent: AndroidDepsProvider

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.init(this)
    }

    override fun getAppProvider(): AndroidDepsProvider {
        return appComponent
    }


}