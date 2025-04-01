package com.example.profilesync

import android.content.Context
import androidx.work.*
import kotlinx.coroutines.coroutineScope

class ProfileSyncWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: ProfileRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val unsyncedProfiles = repository.getUnsyncedProfiles()
        for (profile in unsyncedProfiles) {
            repository.syncProfileToServer(profile)
        }
        return@coroutineScope Result.success()
    }
}
