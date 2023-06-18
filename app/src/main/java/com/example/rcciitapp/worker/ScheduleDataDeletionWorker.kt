package com.example.rcciitapp.worker

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.BackoffPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.time.Duration
import javax.inject.Inject

class ScheduleDataDeletionWorker @Inject constructor(private val context: Context) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun deletePreferencesData() {
        val workRequest = OneTimeWorkRequestBuilder<UserResetWorker>()
            .setInitialDelay(Duration.ofMinutes(1))
            .setBackoffCriteria(
                backoffPolicy = BackoffPolicy.LINEAR,
                duration = Duration.ofSeconds(10)
            )
            .build()
        WorkManager.getInstance(context).enqueue(workRequest)
    }
}