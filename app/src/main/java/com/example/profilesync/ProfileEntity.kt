package com.example.profilesync

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profiles")
data class ProfileEntity(
    @PrimaryKey val userId: String,
    val name: String,
    val email: String,
    val profilePic: String,
    val isSynced: Boolean = false // Flag for sync status
)
