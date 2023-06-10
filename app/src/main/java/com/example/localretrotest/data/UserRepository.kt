package com.example.localretrotest.data

import com.example.localretrotest.model.User
import com.example.localretrotest.netwrok.UserApiService

interface UserRepository {
    suspend fun getUsers(): List<User>
}


class DefaultUserRepository(private val userApiService: UserApiService): UserRepository{
    override suspend fun getUsers(): List<User> = userApiService.getUsers()
}