package com.example.rcciitapp

import android.app.Application
import com.example.rcciitapp.data.AppContainer
import com.example.rcciitapp.data.AppContainerImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Rcciit : Application() {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
    }

}