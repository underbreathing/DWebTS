package com.sheverdyaevartem.home.presentation.state

import com.sheverdyaevartem.home.domain.model.AppMetaInfo

sealed interface HomeState {

    data object Loading: HomeState

    data class Content(val content: List<AppMetaInfo>): HomeState

}