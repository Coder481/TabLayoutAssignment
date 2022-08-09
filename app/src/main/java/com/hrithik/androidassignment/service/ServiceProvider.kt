package com.hrithik.androidassignment.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Initialises retrofit client and creating instance of service to fetch data from API
 */
class ServiceProvider {
    val fetchThemeService: FetchThemeService

    init {
        val interceptor = HttpLoggingInterceptor()
            .also { it.level = HttpLoggingInterceptor.Level.BODY }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

        // Retrofit client connecting with BASE_URL, http client, and RxJava adapter factory
        val retrofitClient = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()

        // creating instance of service
        fetchThemeService = retrofitClient.create(FetchThemeService::class.java)
    }

    // Providing ServiceProvider
    companion object {
        private const val BASE_URL = "https://pkmaster.in/master/api/v4/"
        private var serviceProvider: ServiceProvider? = null

        // Instance of ServiceProvider
        // Return this existing instance every time when called
        val instance: ServiceProvider
            get() {
                if (serviceProvider == null) serviceProvider = ServiceProvider()
                return serviceProvider as ServiceProvider
            }
    }
}