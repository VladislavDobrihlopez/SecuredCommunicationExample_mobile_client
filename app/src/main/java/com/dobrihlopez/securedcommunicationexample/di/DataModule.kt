package com.dobrihlopez.securedcommunicationexample.di

import com.dobrihlopez.securedcommunicationexample.data.local.KeysStorageImpl
import com.dobrihlopez.securedcommunicationexample.data.network.ApiService
import com.dobrihlopez.securedcommunicationexample.domain.KeysStorage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
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
abstract class DataModule {
    @Singleton
    @Binds
    abstract fun bindKeyStorage(implementation: KeysStorageImpl): KeysStorage

    companion object {
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
}