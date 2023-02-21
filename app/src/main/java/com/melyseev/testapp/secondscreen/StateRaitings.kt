package com.melyseev.testapp.secondscreen

import com.melyseev.testapp.data.repository.DetailRaiting

sealed class StateRaitings {
    object Error : StateRaitings()
    object Progress : StateRaitings()
    class Result(val listData: List<DetailRaiting>): StateRaitings()
}