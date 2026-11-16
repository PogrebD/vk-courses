package com.pogreb.vk_courses.domain.usecase

import com.pogreb.vk_courses.domain.repository.AppRepository

class GetAppsListUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke() = repository.getApps()
}