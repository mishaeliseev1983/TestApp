package com.melyseev.testapp.data.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface RatingsService {
    @GET("/testAndroidData")
    suspend fun getRatings(): Response<Any>
}