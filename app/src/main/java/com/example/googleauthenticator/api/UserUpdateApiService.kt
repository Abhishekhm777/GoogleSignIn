package com.example.googleauthenticator.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.QueryMap


private const val BASE_URL ="https://script.google.com/";
class UserUpdateApiService {

    private var api: UserUpdateApi


    init {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        api = retrofit.create(UserUpdateApi::class.java)

    }


    suspend fun updateUser(params: Map<String, String>) {
        api.updateUser(params)
    }


    interface UserUpdateApi {
        @GET("macros/s/AKfycbwWKXODCxSAZ-U_4PEZ9Zz0FZioOsHr-DkPy-TeYoGSpHgiU1qnlGLCc5DEi6MBvyRJ/exec")
        suspend fun updateUser(@QueryMap params: Map<String, String>): String
    }

}