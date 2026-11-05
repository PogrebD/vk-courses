package com.pogreb.vk_courses.di

import android.app.Application
import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.data.repository.AppRepositoryImpl
import com.pogreb.vk_courses.domain.repository.AppRepository
import com.pogreb.vk_courses.domain.usecase.GetAppDetailsUseCase
import com.pogreb.vk_courses.domain.usecase.GetAppsListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSimilarAppsApi(): AppApi = AppApi()

    @Provides
    @Singleton
    fun provideGetAppDetailsUseCase(repository: AppRepository): GetAppDetailsUseCase {
        return GetAppDetailsUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetAppListUseCase(repository: AppRepository): GetAppsListUseCase {
        return GetAppsListUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideAppDetailsMapper(): AppDetailsMapper {
        return AppDetailsMapper()
    }

    @Provides
    @Singleton
    fun provideAppMapper(): AppMapper {
        return AppMapper()
    }

    @Provides
    @Singleton
    fun provideAppRepository(
        api: AppApi,
        appMapper: AppMapper,
        appDetailsMapper: AppDetailsMapper,
    ): AppRepository {
        return AppRepositoryImpl(api, appMapper, appDetailsMapper)
    }
}