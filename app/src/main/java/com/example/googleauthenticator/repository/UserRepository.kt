package com.example.googleauthenticator.repository

import com.example.googleauthenticator.api.UserUpdateApiService

class UserRepository(private val userService: UserUpdateApiService = UserUpdateApiService()) {
    suspend fun updateUser(params: Map<String, String>) {
         userService.updateUser(params)
    }
    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance() = instance?: synchronized(this) {
            instance ?: UserRepository().also { instance = it }
        }
    }
}