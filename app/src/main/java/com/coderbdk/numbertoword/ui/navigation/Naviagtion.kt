package com.coderbdk.numbertoword.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.coderbdk.numbertoword.R
import com.coderbdk.numbertoword.ui.home.HomeRoute
import kotlinx.serialization.Serializable


@Serializable
data object Home : NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNavigation() {
    val backStack = rememberNavBackStack(Home)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            onBack = {
                backStack.removeLastOrNull()
            },
            entryProvider = entryProvider {
                entry<Home> {
                    HomeRoute(viewModel = viewModel())
                }
            }
        )
    }
}