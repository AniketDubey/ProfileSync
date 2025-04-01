package com.example.profilesync

import androidx.work.*

class SyncProfileUseCase(private val workManager: WorkManager) {
    fun scheduleSync() {
        val workRequest = OneTimeWorkRequestBuilder<ProfileSyncWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED) // Sync when internet is available
                    .build()
            )
            .build()
        workManager.enqueue(workRequest)
    }
}
