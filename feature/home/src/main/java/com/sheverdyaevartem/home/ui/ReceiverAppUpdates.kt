package com.sheverdyaevartem.home.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ReceiverAppUpdates : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { intent ->
            if (intent.action == Intent.ACTION_PACKAGE_ADDED
                || intent.action == Intent.ACTION_PACKAGE_REPLACED
                || intent.action == Intent.ACTION_PACKAGE_REMOVED
            ) {
                CoroutineScope(Dispatchers.IO).launch {
                    notifyUpdate()
                }
            }
        }
    }

    companion object {
        private val _updatesFlow = MutableSharedFlow<Unit>(replay = 0)
        val updatesFlow = _updatesFlow.asSharedFlow()

        private suspend fun notifyUpdate() {
            _updatesFlow.emit(Unit)
        }
    }

}