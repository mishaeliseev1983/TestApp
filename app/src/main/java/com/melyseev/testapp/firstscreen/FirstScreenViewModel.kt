package com.melyseev.testapp.firstscreen


import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melyseev.testapp.common.DURATION_FIRST_PROGRESS
import com.melyseev.testapp.common.ONE_HUNDRED_PERCENT
import com.melyseev.testapp.common.ONE_SECOND
import com.melyseev.testapp.firstscreen.communications.FirstScreenCommunications
import com.melyseev.testapp.firstscreen.communications.ObserveFirstScreen
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt


class FirstScreenViewModel @Inject constructor(
    private val communications: FirstScreenCommunications
) : ViewModel(), ObserveFirstScreen, FetchProgress {


    var progressStarted = false
    lateinit var jobProgress: Job
    var startValue: Double = 0.0


    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun fetchProgress() {
        if (progressStarted) return
        progressStarted = true

        val timeProgress = ONE_HUNDRED_PERCENT / DURATION_FIRST_PROGRESS
        jobProgress = viewModelScope.launch {
            while (startValue < ONE_HUNDRED_PERCENT) {
                delay(ONE_SECOND)

                startValue += timeProgress
                var percent = startValue.roundToInt()
                if ((ONE_HUNDRED_PERCENT - percent) < timeProgress)
                    percent = ONE_HUNDRED_PERCENT
                communications.showProgress(percent)
            }
            communications.showProgress(ONE_HUNDRED_PERCENT)
        }
    }

    override fun onCleared() {
        communications.showProgress(0)
        startValue= 0.0
        super.onCleared()
    }

}