package com.example.googleauthenticator.repository

import com.example.googleauthenticator.api.UserUpdateApiService
import com.example.googleauthenticator.util.LocationManager
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserUpdateApiService,
    private val locationManager: LocationManager
) {
    suspend fun updateUser(params: Map<String, String>) {
         userService.updateUser(params)
    }



    suspend fun getLocation(): Pair<String, String> {
        return locationManager.getFormatterLocationString()
    }

}