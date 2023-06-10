package com.example.localretrotest.data

import com.example.localretrotest.netwrok.UserApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val userRepository: UserRepository
}

class DefaultContainer: AppContainer{
    private val BASE_URL = "http://192.168.0.100:8000/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: UserApiService by lazy{
        retrofit.create(UserApiService::class.java)
    }

    override val userRepository: UserRepository by lazy {
        DefaultUserRepository(retrofitService)
    }

}