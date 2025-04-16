package com.sheverdyaevartem.app_details.ui.state

import com.sheverdyaevartem.app_details.domain.model.AppDetails

sealed interface DetailsState {

    data object Loading : DetailsState

    data class Content(val appDetails: AppDetails) : DetailsState
}