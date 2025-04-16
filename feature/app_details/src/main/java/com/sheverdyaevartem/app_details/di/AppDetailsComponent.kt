package com.sheverdyaevartem.app_details.di

import com.sheverdyaevartem.app_details.ui.AppDetailsFragment
import com.sheverdyaevartem.core.di.AndroidDepsProvider
import dagger.Component
import javax.inject.Scope

@AppDetailsScope
@Component(
    dependencies = [AndroidDepsProvider::class],
    modules = [RepositoryModule::class]
)
interface AppDetailsComponent {
    companion object {
        fun init(androidDepsProvider: AndroidDepsProvider): AppDetailsComponent {
            return DaggerAppDetailsComponent.factory().create(androidDepsProvider)
        }
    }

    @Component.Factory
    interface Factory {
        fun create(androidDepsProvider: AndroidDepsProvider): AppDetailsComponent
    }

    fun inject(fragment: AppDetailsFragment)
}

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppDetailsScope