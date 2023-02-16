package com.melyseev.testapp.di

import com.melyseev.testapp.data.retrofit.CloudDataSource
import com.melyseev.testapp.data.retrofit.CloudeModule
import com.melyseev.testapp.data.retrofit.RatingsService
import dagger.Module
import dagger.Provides

@Module
class RetrofitModule {
    @Provides
    fun provideRatingsService(): RatingsService {
        return CloudeModule.Base().getService(RatingsService::class.java)
    }

    @Provides
    fun provideCloudDataSource(ratingService: RatingsService): CloudDataSource {
        return CloudDataSource.Base(ratingService)
    }
}