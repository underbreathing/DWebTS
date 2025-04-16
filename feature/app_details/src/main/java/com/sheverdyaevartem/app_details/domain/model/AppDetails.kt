package com.sheverdyaevartem.app_details.domain.model

data class AppDetails(
    val appName: String,
    val packageName: String,
    val versionName: String,
    val checksum: String? = null,
    val iconUri: String
)