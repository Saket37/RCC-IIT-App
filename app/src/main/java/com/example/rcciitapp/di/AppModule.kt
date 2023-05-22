package com.example.rcciitapp.di

import android.app.Application
import android.content.Context
import com.example.rcciitapp.data.remote.ApiService
import com.example.rcciitapp.data.repository.EventRepositoryImpl
import com.example.rcciitapp.data.repository.NewsRepositoryImpl
import com.example.rcciitapp.data.repository.RepositoryImpl
import com.example.rcciitapp.domain.repository.EventRepository
import com.example.rcciitapp.domain.repository.NewsRepository
import com.example.rcciitapp.domain.repository.Repository
import com.example.rcciitapp.observeconnectivity.ConnectivityObserver
import com.example.rcciitapp.observeconnectivity.ConnectivityObserverImpl
import com.example.rcciitapp.observeconnectivity.connectivityManager
import com.example.rcciitapp.utils.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): Repository {
        return RepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }

    @Singleton
    @Provides
    fun providesEventRepository(): EventRepository {
        return EventRepositoryImpl()
    }

    @Singleton
    @Provides
    fun providedNewsRepository(): NewsRepository {
        return NewsRepositoryImpl()
    }
}