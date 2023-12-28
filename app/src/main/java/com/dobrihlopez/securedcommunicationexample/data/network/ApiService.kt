package com.dobrihlopez.securedcommunicationexample.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("data/data-example")
    fun getData(@Body publicKey: String): Response<String>

    companion object {
        const val BASE_URL = "http://10.0.0.1:8080"
    }
}