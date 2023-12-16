package com.example.googleauthenticator.di

import android.app.Application
import com.example.googleauthenticator.api.UserUpdateApiService
import com.example.googleauthenticator.constants.Constants
import com.example.googleauthenticator.repository.UserRepository
import com.example.googleauthenticator.util.LocationManager
import com.example.googleauthenticator.views.sign_in.SignInViewModel
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//modules that belongs to activity scope
@Module
@InstallIn(ActivityRetainedComponent::class) //Using ActivityRetainedComponent scope here instead Application scope as we are having single activity and very small app.
object ActivityModule {

    @Provides
    fun provideViewModel(repository: UserRepository) =SignInViewModel(repository)

    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideConverterFactory(
        gson:Gson
    ): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofitClient(
        baseUrl: String,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .build()
    }


    @Provides
    fun provideRepository(apiService: UserUpdateApiService,locationManager: LocationManager) : UserRepository =
        UserRepository(apiService,locationManager)

    @Provides
    fun apiService(retrofit: Retrofit): UserUpdateApiService =
        retrofit.create(UserUpdateApiService::class.java)


    @Provides
    fun getLocationManager(
        app: Application
    ): LocationManager {
        return LocationManager(
            LocationServices.getFusedLocationProviderClient(app)
        )
    }

}