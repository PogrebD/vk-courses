package com.pogreb.vk_courses

import com.pogreb.vk_courses.data.AppApi
import com.pogreb.vk_courses.data.dto.AppDetailsDto
import com.pogreb.vk_courses.data.dto.AppDto
import com.pogreb.vk_courses.data.mappers.AppDetailsMapper
import com.pogreb.vk_courses.data.mappers.AppMapper
import com.pogreb.vk_courses.data.repository.AppRepositoryImpl
import com.pogreb.vk_courses.domain.entity.App
import com.pogreb.vk_courses.domain.entity.AppDetails
import com.pogreb.vk_courses.domain.entity.Category
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class AppRepositoryImplTest {
    private val api: AppApi = mockk()
    private val appMapper: AppMapper = mockk()
    private val detailsMapper: AppDetailsMapper = mockk()
    private val repo = AppRepositoryImpl(api, appMapper, detailsMapper)

    private val dto1 = AppDto("1", "A", "d", "c", "i")
    private val dto2 = AppDto("2", "B", "d", "c", "i")
    private val app1 = App("1", "A", "d", "c", "i")
    private val app2 = App("2", "B", "d", "c", "i")

    @Test fun `getApps maps each dto`() = runTest {
        coEvery { api.getApps() } returns listOf(dto1, dto2)
        every { appMapper.map(dto1) } returns app1
        every { appMapper.map(dto2) } returns app2

        assertEquals(listOf(app1, app2), repo.getApps())
        verify(exactly = 1) { appMapper.map(dto1) }
        verify(exactly = 1) { appMapper.map(dto2) }
    }

    @Test fun `getApps propagates api exception`() = runTest {
        coEvery { api.getApps() } throws IOException("net")
        val err = runCatching { repo.getApps() }.exceptionOrNull()
        assertTrue(err is IOException)
    }

    @Test fun `getAppDetails passes id and maps result`() = runTest {
        val dto = AppDetailsDto("1", "N", "D", Category.SOCIAL, 12, 50f, "i",
            listOf("s"), "d")
        val details = AppDetails("1", "N", "D", Category.SOCIAL, 12, 50f, "i",
            listOf("s"), "d")
        coEvery { api.getAppDetails("10") } returns dto
        every { detailsMapper.map(dto) } returns details

        assertEquals(details, repo.getAppDetails("10"))
        coVerify(exactly = 1) { api.getAppDetails("10") }
    }

    @Test fun `loadRatingsSequentially builds correct map`() = runTest {
        coEvery { api.getRating("1") } returns 4.5f
        coEvery { api.getRating("2") } returns 3f

        assertEquals(mapOf("1" to 4.5f, "2" to 3f),
            repo.loadRatingsSequentially(listOf("1", "2")))
    }

    @Test fun `loadRatingsSequentially calls api in given order`() = runTest {
        coEvery { api.getRating(any()) } returns 1f
        repo.loadRatingsSequentially(listOf("a", "b", "c"))
        coVerifyOrder {
            api.getRating("a"); api.getRating("b"); api.getRating("c")
        }
    }

    @Test fun `loadRatingsInParallel builds correct map`() = runTest {
        coEvery { api.getRating("1") } returns 4.5f
        coEvery { api.getRating("2") } returns 3f

        assertEquals(mapOf("1" to 4.5f, "2" to 3f),
            repo.loadRatingsInParallel(listOf("1", "2")))
    }

    @Test fun `loadRatingsInParallel calls api once per id`() = runTest {
        coEvery { api.getRating(any()) } returns 1f
        repo.loadRatingsInParallel(listOf("a", "b", "c"))
        coVerify(exactly = 1) { api.getRating("a") }
        coVerify(exactly = 1) { api.getRating("b") }
        coVerify(exactly = 1) { api.getRating("c") }
    }

    @Test fun `loadRatingsInParallel propagates exception`() = runTest {
        coEvery { api.getRating("bad") } throws IOException("timeout")
        val err = runCatching { repo.loadRatingsInParallel(listOf("bad")) }.exceptionOrNull()
        assertTrue(err is IOException)
    }
}