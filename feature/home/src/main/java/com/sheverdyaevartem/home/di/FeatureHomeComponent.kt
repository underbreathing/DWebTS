package com.sheverdyaevartem.home.di

import com.sheverdyaevartem.core.di.AndroidDepsProvider
import com.sheverdyaevartem.database.di.AppInfoRepositoryModule
import com.sheverdyaevartem.database.di.DbModule
import com.sheverdyaevartem.home.presentation.HomeFragment
import dagger.Component
import javax.inject.Scope

@FeatureHomeScope
@Component(
    dependencies = [AndroidDepsProvider::class],
    modules = [
        AppInfoRepositoryModule::class,
        DbModule::class,
        RepositoryModule::class,
    ]
)
interface FeatureHomeComponent {

    companion object {
        fun init(androidDepsProvider: AndroidDepsProvider): FeatureHomeComponent {
            return DaggerFeatureHomeComponent.factory().create(androidDepsProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(androidDepsProvider: AndroidDepsProvider): FeatureHomeComponent
    }

    fun inject(fragment: HomeFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureHomeScope