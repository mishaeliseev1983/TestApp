package com.melyseev.testapp.secondscreen

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.testapp.common.*
import com.melyseev.testapp.data.repository.DetailRaiting
import com.melyseev.testapp.secondscreen.communications.ObserveSecondScreen
import com.melyseev.testapp.secondscreen.communications.SecondScreenCommunications
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class SecondScreenViewModel @Inject constructor(
    private val ratingsRepositoryBase: RatingsRepository,
    private val communications: SecondScreenCommunications
) : ViewModel(), ObserveSecondScreen {

    private var clock: Int = SECONDS_IN_HOUR
    private var startedTimer = false
    fun startTimer() {
        if (startedTimer) return
        startedTimer = true
        viewModelScope.launch {
            while (clock >= 0) {
                communications.showDataClock(clock)
                delay(ONE_SECOND)
                clock -= 1
                if (clock < 0) clock = MAX_SECONDS_IN_DAY
            }
        }
    }


    var startValue1 = 0.0
    var progressStarted1 = false
    fun goProgress1() {
        if (progressStarted1) return
        progressStarted1 = true

        val durationProgress1 = (5..25).random()
        val timeProgress = ONE_HUNDRED_PERCENT / durationProgress1
        viewModelScope.launch(Dispatchers.IO) {
            while (startValue1 < ONE_HUNDRED_PERCENT) {
                delay(HALF_ONE_SECOND)
                startValue1 += timeProgress
                var percent = startValue1.roundToInt()
                if ((ONE_HUNDRED_PERCENT - percent) < timeProgress)
                    percent = ONE_HUNDRED_PERCENT
                communications.showProgress1(percent)
            }
            startValue1 = 0.0
            progressStarted1 = false
        }
    }


    var startValue2 = 0.0
    var progressStarted2 = false

    fun goProgress2() {
        if (progressStarted2) return
        progressStarted2 = true

        val durationProgress2 = (5..25).random()
        val timeProgress = ONE_HUNDRED_PERCENT / durationProgress2
        viewModelScope.launch(Dispatchers.IO) {
            while (startValue2 < ONE_HUNDRED_PERCENT) {
                delay(HALF_ONE_SECOND)
                startValue2 += timeProgress
                var percent = startValue2.roundToInt()
                if ((ONE_HUNDRED_PERCENT - percent) < timeProgress)
                    percent = ONE_HUNDRED_PERCENT
                communications.showProgress2(percent)
            }
            startValue2 = 0.0
            progressStarted2 = false
        }
    }

    fun getRatings() {
        viewModelScope.launch {
            try {
                communications.showDataRaitings(ratingsRepositoryBase.getRatings())
            } catch (e: java.lang.IllegalStateException) {
                communications.showDataError(true)
            }
        }
    }

    override fun observeProgress1(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress1(owner, observer)
    }

    override fun observeProgress2(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress2(owner, observer)
    }

    override fun observeDataClock(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeDataClock(owner, observer)
    }

    override fun observeListRaitings(
        owner: LifecycleOwner,
        observer: Observer<List<DetailRaiting>>
    ) {
        communications.observeListRaitings(owner, observer)
    }

    override fun observeDataError(owner: LifecycleOwner, observer: Observer<Boolean>) {
        communications.observeDataError(owner, observer)
    }

}