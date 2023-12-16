package com.example.googleauthenticator.api

import retrofit2.http.GET
import retrofit2.http.QueryMap


interface UserUpdateApiService {
        @GET("macros/s/AKfycbwWKXODCxSAZ-U_4PEZ9Zz0FZioOsHr-DkPy-TeYoGSpHgiU1qnlGLCc5DEi6MBvyRJ/exec")
        suspend fun updateUser(@QueryMap params: Map<String, String>): String
    }

