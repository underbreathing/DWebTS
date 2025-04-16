package com.sheverdyaevartem.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sheverdyaevartem.database.entity.AppInfoEntity

@Database(entities = [AppInfoEntity::class], version = 1)
abstract class AppInfoDb : RoomDatabase() {

    abstract fun getAppInfoDao(): AppInfoDao

}
