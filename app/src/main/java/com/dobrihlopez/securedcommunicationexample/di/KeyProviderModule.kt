package com.dobrihlopez.securedcommunicationexample.di

import com.dobrihlopez.securedcommunicationexample.data.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KeyProviderModule {
    @Provides
    @Singleton
    fun provideFeature(): ApiService {
        return Retrofit.Builder()
            .baseUrl(ApiService.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(ApiService::class.java)
    }
}