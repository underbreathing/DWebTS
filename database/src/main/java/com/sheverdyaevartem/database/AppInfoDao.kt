package com.sheverdyaevartem.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sheverdyaevartem.database.entity.AppInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AppInfoDao {

    @Query("DELETE FROM AppInfoEntity")
    abstract fun clearAppsInfo()

    @Query("SELECT * FROM AppInfoEntity")
    abstract fun getAppsInfo(): Flow<List<AppInfoEntity>>

//    @Query("DELETE FROM AppInfoEntity WHERE packageName =:packageName")
//    suspend fun deleteAppInfo(packageName: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAppInfo(appInfoEntity: AppInfoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAppInfoList(appInfoEntities: List<AppInfoEntity>)

}