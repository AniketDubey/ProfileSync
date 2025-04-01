package com.example.profilesync

class UpdateProfileUseCase(private val repository: ProfileRepository) {
    suspend fun execute(profile: ProfileEntity) {
        repository.updateProfileLocally(profile) // Save locally first
    }
}
