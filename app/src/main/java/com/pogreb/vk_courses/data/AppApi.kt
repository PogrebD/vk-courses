package com.pogreb.vk_courses.data

import com.pogreb.vk_courses.data.dto.AppDetailsDto
import com.pogreb.vk_courses.data.dto.AppDto
import com.pogreb.vk_courses.domain.entity.Category
import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path
import kotlin.time.Duration.Companion.milliseconds

interface AppApi {
    @GET("catalog")
    suspend fun getApps() : List<AppDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String) : AppDetailsDto

    suspend fun getRating(id: String): Float {
        delay(150.milliseconds)
        return (30..50).random() / 10f
    }
}