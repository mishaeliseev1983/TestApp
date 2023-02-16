package com.melyseev.testapp.data.retrofit

import retrofit2.Response
import javax.inject.Inject

interface CloudDataSource {
    suspend fun getRatings(): Response<Any>

    class Base @Inject constructor(private val service: RatingsService): CloudDataSource {
        override suspend fun getRatings(): Response<Any>
                                        = service.getRatings()
    }
}