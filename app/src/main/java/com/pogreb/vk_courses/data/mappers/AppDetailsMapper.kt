package com.pogreb.vk_courses.data.mappers

import com.pogreb.vk_courses.data.dto.AppDetailsDto
import com.pogreb.vk_courses.data.dto.AppDto
import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails

class AppDetailsMapper {
    fun map(dto: AppDetailsDto): AppDetails =
        AppDetails(
            id = dto.id,
            name = dto.name,
            developer = dto.developer,
            category = dto.category,
            ageRating = dto.ageRating,
            size = dto.size,
            iconUrl = dto.iconUrl,
            screenshotUrlList = dto.screenshotUrlList,
            description = dto.description
        )
}