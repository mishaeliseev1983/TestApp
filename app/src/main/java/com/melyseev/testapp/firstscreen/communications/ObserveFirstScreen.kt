package com.melyseev.testapp.firstscreen.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

interface ObserveFirstScreen {
    fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>)
}