package com.coderbdk.numbertoword.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(
        uiState = uiState,
        showType = viewModel::showType,
        onTypeSelected = viewModel::onTypeSelected,
        onInputValueChange = viewModel::onInputValueChange,
        onValueSwap = viewModel::onValueSwap,
        convert = viewModel::convert
    )
}