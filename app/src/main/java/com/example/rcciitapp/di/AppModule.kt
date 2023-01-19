package com.example.rcciitapp.di

import android.app.Application
import com.example.rcciitapp.observeconnectivity.ConnectivityObserver
import com.example.rcciitapp.observeconnectivity.ConnectivityObserverImpl
import com.example.rcciitapp.observeconnectivity.connectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideConnectivityObserver(application: Application): ConnectivityObserver {
        return ConnectivityObserverImpl(application.connectivityManager)
    }
}