package com.sheverdyaevartem.dwebts

import android.app.Application
import com.sheverdyaevartem.core.di.App
import com.sheverdyaevartem.core.di.AppProvider
import com.sheverdyaevartem.daggerpractic.di.AppComponent
import com.sheverdyaevartem.daggerpractic.di.DaggerAppComponent

class App: Application(),App {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
    }

    override fun getAppProvider(): AppProvider {
        return appComponent
    }


}