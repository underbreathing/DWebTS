package com.sheverdyaevartem.app_details.ui

import android.telecom.Call.Details
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.app_details.domain.AppDetailsUseCase
import com.sheverdyaevartem.app_details.domain.model.AppDetails
import com.sheverdyaevartem.app_details.ui.state.DetailsState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppDetailsViewModel (
    private val packageName: String,
    private val appDetailsUseCase: AppDetailsUseCase
) : ViewModel() {
    init {
        getAppDetails()
    }

    companion object {
        const val PACKAGE_NAME_KEY = "user_id"
    }

    val detailsState: StateFlow<DetailsState> get() = _detailsState
    private val _detailsState: MutableStateFlow<DetailsState> = MutableStateFlow(DetailsState.Loading)


    @Suppress("UNCHECKED_CAST")
    class AppDetailsViewModelFactory @AssistedInject constructor(
        @Assisted(PACKAGE_NAME_KEY) private val packageName: String,
        private val appDetailsUseCase: AppDetailsUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == AppDetailsViewModel::class.java)
            return AppDetailsViewModel(packageName, appDetailsUseCase) as T
        }

        @AssistedFactory
        interface Factory {
            fun create(@Assisted(PACKAGE_NAME_KEY) packageName: String): AppDetailsViewModel.AppDetailsViewModelFactory
        }
    }

    private fun getAppDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _detailsState.emit(DetailsState.Content(appDetailsUseCase(packageName)))
        }
    }
}
