package com.example.localretrotest.ui.utils

import com.example.localretrotest.model.User

sealed interface UserUiState {
    data class Success(val users: List<User>): UserUiState
    object Error: UserUiState
    object Loading: UserUiState
}