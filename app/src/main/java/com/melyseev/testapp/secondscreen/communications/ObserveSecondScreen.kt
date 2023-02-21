package com.melyseev.testapp.secondscreen.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.testapp.secondscreen.StateRaitings

interface ObserveSecondScreen {
    fun observeProgress1(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeProgress2(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeDataClock(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeStateRaitings(owner: LifecycleOwner, observer: Observer<StateRaitings>)
}