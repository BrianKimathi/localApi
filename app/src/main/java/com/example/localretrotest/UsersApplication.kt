package com.example.localretrotest

import android.app.Application
import com.example.localretrotest.data.AppContainer
import com.example.localretrotest.data.DefaultContainer

class UsersApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}