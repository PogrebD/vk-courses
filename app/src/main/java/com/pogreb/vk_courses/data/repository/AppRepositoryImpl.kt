package com.pogreb.vk_courses.data.repository

import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails
import com.pogreb.vk_courses.domain.repository.AppRepository
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
}