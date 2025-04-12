package com.sheverdyaevartem.home.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob


class HomeViewModel : ViewModel() {

    companion object {
        const val USER_ID_KEY = "user_id"
    }

    val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun fetchData() {

    }


}