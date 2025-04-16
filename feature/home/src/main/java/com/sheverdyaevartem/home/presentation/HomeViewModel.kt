package com.sheverdyaevartem.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sheverdyaevartem.home.domain.AppsCacheInteractor
import com.sheverdyaevartem.home.domain.GetAppsUseCase
import com.sheverdyaevartem.home.domain.model.AppMetaInfo
import com.sheverdyaevartem.home.presentation.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel(
    private val getAppsUseCase: GetAppsUseCase,
    private val appsCacheInteractor: AppsCacheInteractor,
) :
    ViewModel() {
    init {
        updateCache()
        getApps()
    }

    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)

    val homeState: StateFlow<HomeState> get() = _homeState

    fun updateCache() {
        viewModelScope.launch(Dispatchers.IO) {
            appsCacheInteractor.updateAppsInfo(getAppsUseCase())
        }
    }

    class HomeViewModelFactory @Inject constructor(
        private val getAppsUseCase: GetAppsUseCase,
        private val appsCacheInteractor: AppsCacheInteractor,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == HomeViewModel::class.java) {
                "Wrong ViewModel class: ${modelClass.simpleName}"
            }
            return HomeViewModel(getAppsUseCase, appsCacheInteractor) as T
        }
    }

    private fun getApps() {
        viewModelScope.launch(Dispatchers.IO) {
            appsCacheInteractor.getAppsInfoCache().collect { listAppInfo: List<AppMetaInfo> ->
                _homeState.emit(HomeState.Content(listAppInfo))
            }
        }
    }
}
