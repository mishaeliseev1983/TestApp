package com.melyseev.testapp.secondscreen.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.testapp.data.repository.DetailRaiting

interface ObserveSecondScreen {
    fun observeProgress1(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeProgress2(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeDataClock(owner: LifecycleOwner, observer: Observer<Int>)
    fun observeListRaitings(owner: LifecycleOwner, observer: Observer<List<DetailRaiting>>)
    fun observeDataError(owner: LifecycleOwner, observer: Observer<Boolean>)
}