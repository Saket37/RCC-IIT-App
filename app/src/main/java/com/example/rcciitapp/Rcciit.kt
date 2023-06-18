package com.example.rcciitapp

import android.app.Application
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.work.BackoffPolicy
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.rcciitapp.data.AppContainer
import com.example.rcciitapp.data.AppContainerImpl
import com.example.rcciitapp.utils.SharedPreferenceManager
import com.example.rcciitapp.worker.UserResetWorker
import dagger.hilt.android.HiltAndroidApp
import java.time.Duration
import javax.inject.Inject

@HiltAndroidApp
class Rcciit : Application(), Configuration.Provider {
    // AppContainer instance used by the rest of classes to obtain dependencies
    lateinit var container: AppContainer

    @Inject
    lateinit var workerFactory: CustomWorkerFactory

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        container = AppContainerImpl(this)
    }

    override fun getWorkManagerConfiguration(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()


}

class CustomWorkerFactory @Inject constructor(
    private val sharedPreferenceManager: SharedPreferenceManager
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker =
        UserResetWorker(appContext, workerParameters, sharedPreferenceManager)

}