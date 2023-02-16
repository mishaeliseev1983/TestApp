package com.melyseev.testapp.di

import android.content.Context
import com.melyseev.testapp.firstscreen.FirstScreenFragment
import com.melyseev.testapp.secondscreen.SecondScreenFragment
import dagger.BindsInstance
import dagger.Component


@ApplicationScope
@Component(
    modules = [
        ViewModelModule::class,
        RetrofitModule::class,
        CommunicationsModule::class,
        ViewModelDependenciesModule::class,
        AppModule::class]
)
interface ApplicationComponent {

    fun inject(firstScreenFragment: FirstScreenFragment)
    fun inject(secondScreenFragment: SecondScreenFragment)

    @Component.Factory
    interface AppCompFactory {
        fun create(
            @BindsInstance context: Context
        ): ApplicationComponent
    }
}