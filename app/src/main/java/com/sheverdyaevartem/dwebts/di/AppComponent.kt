package com.sheverdyaevartem.daggerpractic.di

import android.app.Application
import com.sheverdyaevartem.core.di.AndroidDepsProvider
import com.sheverdyaevartem.core.di.android.AndroidDepsComponent
import dagger.Component



@Component(dependencies = [AndroidDepsProvider::class])
interface AppComponent : AndroidDepsProvider {

    companion object {
        fun init(app: Application): AndroidDepsProvider {
            return DaggerAppComponent.factory().create(
                AndroidDepsComponent.init(app)
            )
        }
    }

    @Component.Factory
    interface Factory {
        fun create(androidDepsProvider: AndroidDepsProvider): AppComponent
    }
}