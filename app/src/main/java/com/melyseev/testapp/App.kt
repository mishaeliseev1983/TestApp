package com.melyseev.testapp

import android.app.Application
import com.melyseev.testapp.di.DaggerApplicationComponent

class App: Application() {
    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}