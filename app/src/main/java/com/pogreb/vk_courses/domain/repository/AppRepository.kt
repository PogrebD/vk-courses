package com.pogreb.vk_courses.domain.repository

import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails

interface AppRepository {
    suspend fun getApps(): List<App>
    suspend fun getAppDetails(id: String): AppDetails
}