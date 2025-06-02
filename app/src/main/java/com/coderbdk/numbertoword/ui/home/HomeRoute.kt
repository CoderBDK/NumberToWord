package com.coderbdk.numbertoword.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import java.util.Locale

@Composable
fun HomeRoute(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        menuTypes = viewModel.menuTypes,
        showType = viewModel::showType,
        onTypeSelected = viewModel::onTypeSelected,
        onInputValueChange = viewModel::onInputValueChange,
        onValueSwap = viewModel::onValueSwap,
        convert = viewModel::convert
    )
}