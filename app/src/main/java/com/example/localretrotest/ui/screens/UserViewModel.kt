package com.example.localretrotest.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.localretrotest.UsersApplication
import com.example.localretrotest.data.UserRepository
import com.example.localretrotest.ui.utils.UserUiState
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    init {
        getUsers()
    }

    fun getUsers(){
        viewModelScope.launch {
            userUiState = UserUiState.Loading

            userUiState = try {
                UserUiState.Success(userRepository.getUsers())
            }catch (e: IOException){
                Log.e("Error", e.message.toString())
                UserUiState.Error
            }catch (e: HttpException){
                Log.e("Error", e.message.toString())
                UserUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as UsersApplication)
                val userRepository = application.container.userRepository
                UserViewModel(userRepository = userRepository)
            }
        }
    }

}