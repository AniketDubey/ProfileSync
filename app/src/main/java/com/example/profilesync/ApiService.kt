package com.example.profilesync

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("profile/update")
    suspend fun updateProfile(@Body profile: ProfileEntity): Response<Unit>
}
