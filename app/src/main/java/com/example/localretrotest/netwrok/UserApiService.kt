package com.example.localretrotest.netwrok

import com.example.localretrotest.model.User
import retrofit2.http.GET

interface UserApiService {
    @GET("items")
    suspend fun getUsers(): List<User>
}