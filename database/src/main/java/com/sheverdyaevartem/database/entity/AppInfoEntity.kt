package com.sheverdyaevartem.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppInfoEntity(
    @PrimaryKey
    val packageName: String,
    val appName: String,
    val appIconUri: String,
)
