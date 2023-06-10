package com.example.localretrotest.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.localretrotest.R
import com.example.localretrotest.ui.screens.HomeScreen
import com.example.localretrotest.ui.screens.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersApp(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { AppBar() }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background
        ) {
            val usersViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
            HomeScreen(
                userUiState = usersViewModel.userUiState,
                retryAction = usersViewModel::getUsers
            )
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.app_top_name),
                style = MaterialTheme.typography.titleMedium
            )
        }
    )
}