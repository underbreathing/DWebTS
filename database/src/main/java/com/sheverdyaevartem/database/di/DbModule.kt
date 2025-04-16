package com.sheverdyaevartem.database.di

import android.content.Context
import androidx.room.Room
import com.sheverdyaevartem.database.AppInfoDb
import com.sheverdyaevartem.database.R
import dagger.Module
import dagger.Provides

@Module
class DbModule {

    private val database: AppInfoDb? = null

    @Provides
    fun provideDb(appContext: Context): AppInfoDb {
        return database ?: Room.databaseBuilder(
            appContext, AppInfoDb::class.java, appContext.getString(R.string.app_info_db)
        ).build()
    }

}