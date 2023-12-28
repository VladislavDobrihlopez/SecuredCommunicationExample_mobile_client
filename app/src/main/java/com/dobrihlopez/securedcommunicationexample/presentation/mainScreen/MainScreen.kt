package com.dobrihlopez.securedcommunicationexample.presentation.mainScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    MainScreenContent(state = state, onFetchData = {
        viewModel.onIntent(ScreenIntent.FetchData)
    })
}