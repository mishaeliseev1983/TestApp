package com.melyseev.testapp.firstscreen.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.testapp.common.Communications
import javax.inject.Inject

interface FirstScreenCommunications: ObserveFirstScreen {
    fun showProgress(value: Int)


    class Base @Inject constructor( private val progress: Communications.IntCommunication): FirstScreenCommunications{
        override fun showProgress(value: Int) {
            progress.change(value)
        }
        override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
            progress.observe(owner, observer)
        }
    }
}