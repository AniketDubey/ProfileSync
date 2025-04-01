package com.example.profilesync

import kotlinx.coroutines.flow.Flow

class ProfileRepository(
    private val apiService: ApiService,
    private val profileDao: ProfileDao
) {
    fun getProfiles(): Flow<List<ProfileEntity>> {
        return profileDao.getAllProfiles()
    }

    suspend fun updateProfileLocally(profile: ProfileEntity) {
        profileDao.saveProfile(profile.copy(isSynced = false)) // Mark as unsynced
    }

    suspend fun syncProfileToServer(profile: ProfileEntity) {
        try {
            val response = apiService.updateProfile(profile)
            if (response.isSuccessful) {
                profileDao.saveProfile(profile.copy(isSynced = true)) // Mark as synced
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getUnsyncedProfiles(): List<ProfileEntity> {
        return profileDao.getUnsyncedProfiles()
    }
}
