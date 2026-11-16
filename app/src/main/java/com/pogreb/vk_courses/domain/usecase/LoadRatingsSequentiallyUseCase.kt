package com.pogreb.vk_courses.domain.usecase

import com.pogreb.vk_courses.domain.repository.AppRepository

class LoadRatingsSequentiallyUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(ids: List<String>) = repository.loadRatingsSequentially(ids)
}