package com.example.rcciitapp.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.rcciitapp.utils.SharedPreferenceManager
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class UserResetWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
     private val sharedPreferenceManager: SharedPreferenceManager
) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        return try {
            sharedPreferenceManager.deleteUserData()
            Result.success()

        } catch (e: Exception) {
            Log.d("WorkError", e.message.toString())
            Result.failure(Data.Builder().putString("Error", e.toString()).build())
        }
    }
}