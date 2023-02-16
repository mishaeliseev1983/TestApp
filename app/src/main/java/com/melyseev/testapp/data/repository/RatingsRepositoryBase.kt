package com.melyseev.testapp.data.repository

import com.google.gson.internal.LinkedTreeMap
import com.melyseev.testapp.common.IMAGE
import com.melyseev.testapp.common.TITLE
import com.melyseev.testapp.data.retrofit.CloudDataSource
import com.melyseev.testapp.secondscreen.RatingsRepository
import javax.inject.Inject

class RatingsRepositoryBase @Inject constructor(
    private val cloudDataSource: CloudDataSource
): RatingsRepository {

    override suspend fun getRatings(): List<DetailRaiting> {
        val returnList = mutableListOf<DetailRaiting>()

        try {
            val firstLevelTree: LinkedTreeMap<String, Any> =
                cloudDataSource.getRatings().body() as LinkedTreeMap<String, Any>
            for (keyFirst in firstLevelTree.keys) {
                val secondLevelTree: LinkedTreeMap<String, Any> =
                    firstLevelTree[keyFirst] as LinkedTreeMap<String, Any>
                for (keySecond in secondLevelTree.keys) {
                    val m = secondLevelTree[keySecond] as LinkedTreeMap<String, Any>
                    val image = m[IMAGE] as String
                    val title = m[TITLE] as String
                    returnList.add(DetailRaiting(image, title))
                }
            }
            return returnList

        } catch (e: Exception) {
            throw  java.lang.IllegalStateException()
        }
    }
}