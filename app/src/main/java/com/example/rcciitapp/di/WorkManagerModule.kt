package com.example.rcciitapp.di

import android.content.Context
import com.example.rcciitapp.CustomWorkerFactory
import com.example.rcciitapp.utils.SharedPreferenceManager
import com.example.rcciitapp.worker.ScheduleDataDeletionWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WorkManagerModule {
    @Singleton
    @Provides
    fun providesPreferenceWorkManager(@ApplicationContext applicationContext: Context) =
        ScheduleDataDeletionWorker(applicationContext)

    @Singleton
    @Provides
    fun providesPreference(pref:SharedPreferenceManager) = CustomWorkerFactory(pref)
}