package com.melyseev.testapp.firstscreen

import androidx.lifecycle.*
import com.melyseev.testapp.common.*
import com.melyseev.testapp.data.repository.RatingsRepositoryBase
import com.melyseev.testapp.data.repository.DetailRaiting
import com.melyseev.testapp.firstscreen.communications.FirstScreenCommunications
import com.melyseev.testapp.firstscreen.communications.ObserveFirstScreen


import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt


class FirstScreenViewModel @Inject constructor(
    private val communications: FirstScreenCommunications
): ViewModel(), ObserveFirstScreen, FetchProgress {

    var progressStarted = false
    lateinit var  jobProgress: Job
    private var startValue = 0.0

    override fun observeProgress(owner: LifecycleOwner, observer: Observer<Int>) {
        communications.observeProgress(owner, observer)
    }

    override fun fetchProgress() {
        if(progressStarted) return
        progressStarted = true

        val timeProgress = ONE_HUNDRED_PERCENT / DURATION_FIRST_PROGRESS
        jobProgress = viewModelScope.launch(IO) {
            while (startValue< ONE_HUNDRED_PERCENT) {
                delay(HALF_ONE_SECOND)
                startValue += timeProgress
                var percent = startValue.roundToInt()
                if((ONE_HUNDRED_PERCENT - percent) < timeProgress)
                    percent = ONE_HUNDRED_PERCENT
                communications.showProgress( percent )
            }
            communications.showProgress(ONE_HUNDRED_PERCENT)
        }
    }

}