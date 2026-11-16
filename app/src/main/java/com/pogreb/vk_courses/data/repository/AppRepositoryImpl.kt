package com.pogreb.vk_courses.data.repository

import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails
import com.pogreb.vk_courses.domain.repository.AppRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val api: AppApi,
    private val appMapper: AppMapper,
    private val appDetailsMapper: AppDetailsMapper,
) : AppRepository {
    override suspend fun getApps(): List<App> =
        api.getApps().map { appMapper.map(it) }

    override suspend fun getAppDetails(id: String): AppDetails =
        appDetailsMapper.map(api.getAppDetails(id))

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val result = mutableMapOf<String, Float>()
        for (id in ids) {
            result[id] = api.getRating(id)
        }
        return result
    }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> =
        coroutineScope {
            ids.map { id ->
                async { id to api.getRating(id) }
            }.awaitAll().toMap()
        }
}