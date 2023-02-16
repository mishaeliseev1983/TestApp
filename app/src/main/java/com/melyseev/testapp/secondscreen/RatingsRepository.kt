package com.melyseev.testapp.secondscreen

import com.melyseev.testapp.data.repository.DetailRaiting

interface RatingsRepository {
    suspend fun getRatings(): List<DetailRaiting>
}