package com.example.profilesync

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: ProfileRepository,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val syncProfileUseCase: SyncProfileUseCase
) : ViewModel() {

    val profileList: StateFlow<List<ProfileEntity>> = repository.getProfiles()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun updateProfile(profile: ProfileEntity) {
        viewModelScope.launch {
            updateProfileUseCase.execute(profile)
            syncProfileUseCase.scheduleSync() // Schedule sync
        }
    }
}
