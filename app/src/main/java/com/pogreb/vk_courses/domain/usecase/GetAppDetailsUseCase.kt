package com.pogreb.vk_courses.domain.usecase

import com.pogreb.vk_courses.domain.repository.AppRepository

class GetAppDetailsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(id: String) = repository.getAppDetails(id)
}