package com.example.googleauthenticator.repository

import com.example.googleauthenticator.api.UserUpdateApiService
import com.example.googleauthenticator.util.LocationManager
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserUpdateApiService,
    private val locationManager: LocationManager
) {
    suspend fun updateUser(params: Map<String, String>) {
        try {
            userService.updateUser(params)
        }catch (e:Exception){
            e.printStackTrace()
        }

    }



    suspend fun getLocation(): Pair<String, String> {
        return try {
            locationManager.getFormatterLocationString()
        } catch (e:Exception){
            e.printStackTrace()
            Pair("", "")
        }
    }

}