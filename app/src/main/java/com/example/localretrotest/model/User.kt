package com.example.localretrotest.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name: String,
    val age: Int,
    val phone: String
)
