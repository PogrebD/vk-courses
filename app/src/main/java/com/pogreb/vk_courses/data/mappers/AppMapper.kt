package com.pogreb.vk_courses.data.mappers

import com.pogreb.vk_courses.data.dto.AppDto
import com.pogreb.vk_courses.domain.entity.App

class AppMapper {
    fun map(dto: AppDto): App =
        App(
            id = dto.id,
            name = dto.name,
            description = dto.description,
            category = dto.category,
            iconUrl = dto.iconUrl
        )
}