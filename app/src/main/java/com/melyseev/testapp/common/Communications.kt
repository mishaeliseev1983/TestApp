package com.melyseev.testapp.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.melyseev.testapp.data.repository.DetailRaiting
import javax.inject.Inject


interface Communications {

    interface Mapper<R, S> {
        fun map(source: S): R
    }

    // interface Change<S> : Mapper<Unit, S>
    // interface Mutable<T> : Observe<T>, Change<T>

    interface Change<Unit, S> {
        fun change(source: S): Unit
    }

    interface Observe<T> {
        fun observe(owner: LifecycleOwner, observer: Observer<T>)
    }


    interface Mutable<T> : Observe<T>, Change<Unit, T>

    abstract class Abstract<T>(val liveData: MutableLiveData<T>) : Mutable<T> {
        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            liveData.observe(owner, observer)
        }
    }

    abstract class Post<T>(liveData: MutableLiveData<T> = MutableLiveData()) :
        Abstract<T>(liveData) {
        override fun change(source: T) {
            source.let {
                liveData.postValue(it)
            }
        }
    }

    interface IntCommunication : Mutable<Int> {
        class Base @Inject constructor() : IntCommunication, Post<Int>()
    }

    interface DataRaitingsCommunication : Mutable<List<DetailRaiting>> {
        class Base @Inject constructor() : DataRaitingsCommunication, Post<List<DetailRaiting>>()
    }

    interface BooleanCommunication : Mutable<Boolean> {
        class Base @Inject constructor() : BooleanCommunication, Post<Boolean>()
    }

}
