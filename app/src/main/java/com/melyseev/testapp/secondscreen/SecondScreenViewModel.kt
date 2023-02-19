package com.melyseev.testapp.secondscreen

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.testapp.common.MAX_SECONDS_IN_DAY
import com.melyseev.testapp.common.ONE_HUNDRED_PERCENT
import com.melyseev.testapp.common.ONE_SECOND
import com.melyseev.testapp.common.SECONDS_IN_HOUR
import com.melyseev.testapp.data.repository.DetailRaiting
import com.melyseev.testapp.secondscreen.communications.ObserveSecondScreen
import com.melyseev.testapp.secondscreen.communications.SecondScreenCommunications
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

class SecondScreenViewModel @Inject constructor(
    private val ratingsRepositoryBase: RatingsRepository,
    private val communications: SecondScreenCommunications
) : ViewModel(), ObserveSecondScreen, FetchSecondScreenElements {

    private var startValue1 = 0.0
    private var startValue2 = 0.0
    private var clock: Int = SECONDS_IN_HOUR

    init {
        communications.showProgress1(0)
        communications.showProgress2(0)
        communications.showDataError(false)
    }


    fun goProgress1() {
        val durationProgress1 = (5..25).random()
        val timeProgress = ONE_HUNDRED_PERCENT / durationProgress1
        viewModelScope.launch {
            while (startValue1 < ONE_HUNDRED_PERCENT) {
                delay(ONE_SECOND)
                startValue1 += timeProgress
                var percent = startValue1.roundToInt()
                if ((ONE_HUNDRED_PERCENT - percent) < timeProgress)
                    percent = ONE_HUNDRED_PERCENT
                communications.showProgress1(percent)
            }
            startValue1 = 0.0
        }
    }



    fun goProgress2() {
        val durationProgress2 = (5..25).random()
        val timeProgress = ONE_HUNDRED_PERCENT / durationProgress2
        viewModelScope.launch {
            while (startValue2 < ONE_HUNDRED_PERCENT) {
                delay(ONE_SECOND)
                startValue2 += timeProgress
                var percent = startValue2.roundToInt()
                if ((ONE_HUNDRED_PERCENT - percent) < timeProgress)
                    percent = ONE_HUNDRED_PERCENT
                communications.showProgress2(percent)
            }
            startValue2 = 0.0
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

    override fun fetchClock() {
        viewModelScope.launch {
            while (clock >= 0) {
                communications.showDataClock(clock)
                delay(ONE_SECOND)
                clock -= 1
                if (clock < 0) clock = MAX_SECONDS_IN_DAY
            }
        }
    }

    override fun fetchDataRaitings() {
        viewModelScope.launch {
            try {
                communications.showDataRaitings(ratingsRepositoryBase.getRatings())
            } catch (e: java.lang.IllegalStateException) {
                communications.showDataError(true)
            }
        }
    }

}