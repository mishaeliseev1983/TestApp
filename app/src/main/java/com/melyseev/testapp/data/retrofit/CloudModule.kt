package com.melyseev.testapp.data.retrofit

import com.google.gson.GsonBuilder
import com.melyseev.testapp.common.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

interface CloudeModule {
    fun <T> getService(clazz: Class<T>): T

    class Base @Inject constructor()  : CloudeModule {
        override fun <T> getService(clazz: Class<T>): T {

            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            return Retrofit
                .Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(clazz)
        }

    }
}