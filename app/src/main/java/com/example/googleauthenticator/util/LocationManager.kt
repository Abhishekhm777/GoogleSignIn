package com.example.googleauthenticator.util

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.tasks.asDeferred


class LocationManager(
    private val fusedLocationProviderClient: FusedLocationProviderClient
) {

    //coroutine suspend function to fetch current user location
    suspend fun getFormatterLocationString(): Pair<String, String> {

        val location:Location? = getLastLocationAsync().await()
        return Pair("${location?.latitude}", "${location?.longitude}")
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocationAsync() = fusedLocationProviderClient.lastLocation.asDeferred()
}