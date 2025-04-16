package com.sheverdyaevartem.core.di.android

import android.app.Application
import com.sheverdyaevartem.core.di.AndroidDepsProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidDependenciesModule::class
    ]
)
interface AndroidDepsComponent: AndroidDepsProvider {

    companion object {

        fun init(app: Application): AndroidDepsProvider {

            val androidDependenciesModule = AndroidDependenciesModule(app)

            return DaggerAndroidDepsComponent.builder()
                .androidDependenciesModule(androidDependenciesModule)
                .build()
        }
    }
}