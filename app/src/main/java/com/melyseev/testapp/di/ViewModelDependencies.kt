package com.melyseev.testapp.di

import com.melyseev.testapp.data.repository.RatingsRepositoryBase
import com.melyseev.testapp.secondscreen.RatingsRepository
import dagger.Binds
import dagger.Module

@Module
interface ViewModelDependenciesModule {

    @ApplicationScope
    @Binds
    fun provideRepository(param: RatingsRepositoryBase): RatingsRepository
}