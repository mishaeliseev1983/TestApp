package com.melyseev.testapp.di

import androidx.lifecycle.ViewModel
import com.melyseev.testapp.firstscreen.FirstScreenViewModel
import com.melyseev.testapp.secondscreen.SecondScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @IntoMap
    @ViewModelKey(FirstScreenViewModel::class)
    @Binds
    fun bindFirstScreenViewModel(impl: FirstScreenViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SecondScreenViewModel::class)
    @Binds
    fun bindSecondScreenViewModel(impl: SecondScreenViewModel): ViewModel
}