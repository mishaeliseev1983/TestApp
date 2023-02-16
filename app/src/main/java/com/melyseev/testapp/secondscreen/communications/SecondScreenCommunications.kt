package com.melyseev.testapp.secondscreen.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.melyseev.testapp.common.Communications
import com.melyseev.testapp.data.repository.DetailRaiting
import javax.inject.Inject


interface SecondScreenCommunications : ObserveSecondScreen {

    fun showProgress1(value: Int)
    fun showProgress2(value: Int)
    fun showDataClock(value: Int)
    fun showDataRaitings(value: List<DetailRaiting>)
    fun showDataError(value: Boolean)

    class Base @Inject constructor(
        private val progress1: Communications.IntCommunication,
        private val progress2: Communications.IntCommunication,
        private val dataClock: Communications.IntCommunication,
        private val dataRaitings: Communications.DataRaitingsCommunication,
        private val dataError: Communications.BooleanCommunication,
        ) : SecondScreenCommunications {
        override fun showProgress1(value: Int) {
            progress1.change(value)
        }

        override fun showProgress2(value: Int) {
            progress2.change(value)
        }

        override fun showDataClock(value: Int) {
           dataClock.change(value)
        }

        override fun showDataRaitings(value: List<DetailRaiting>) {
           dataRaitings.change(value)
        }

        override fun showDataError(value: Boolean) {
           dataError.change(value)
        }

        override fun observeProgress1(owner: LifecycleOwner, observer: Observer<Int>) {
           progress1.observe(owner, observer)
        }

        override fun observeProgress2(owner: LifecycleOwner, observer: Observer<Int>) {
            progress2.observe(owner, observer)
        }

        override fun observeDataClock(owner: LifecycleOwner, observer: Observer<Int>) {
            dataClock.observe(owner, observer)
        }

        override fun observeListRaitings(
            owner: LifecycleOwner,
            observer: Observer<List<DetailRaiting>>
        ) {
            dataRaitings.observe(owner, observer)
        }

        override fun observeDataError(owner: LifecycleOwner, observer: Observer<Boolean>) {
            dataError.observe(owner, observer)
        }
    }
}