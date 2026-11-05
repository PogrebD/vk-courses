package com.pogreb.vk_courses.domain.repository

import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails

interface AppRepository {
    fun getApps(): List<App>
    fun getAppDetails(): AppDetails
}