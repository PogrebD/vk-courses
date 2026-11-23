package com.pogreb.vk_courses

import com.pogreb.vk_courses.domain.entity.AppDetails
import com.pogreb.vk_courses.domain.entity.Category
import com.pogreb.vk_courses.domain.repository.AppRepository
import com.pogreb.vk_courses.domain.usecase.GetAppDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetAppDetailsUseCaseTest {
    private val repo: AppRepository = mockk()
    private val uc = GetAppDetailsUseCase(repo)
    private val details = AppDetails("1", "N", "D", Category.SOCIAL, 12, 50f, "i",
        listOf("s"), "d")

    @Test fun `invoke returns repository result`() = runTest {
        coEvery { repo.getAppDetails("1") } returns details
        assertEquals(details, uc("1"))
    }

    @Test fun `invoke passes id to repository`() = runTest {
        coEvery { repo.getAppDetails(any()) } returns details
        uc("abc")
        coVerify(exactly = 1) { repo.getAppDetails("abc") }
    }

    @Test fun `invoke handles empty id`() = runTest {
        coEvery { repo.getAppDetails("") } returns details
        assertEquals(details, uc(""))
    }

    @Test fun `invoke propagates exception`() = runTest {
        coEvery { repo.getAppDetails(any()) } throws RuntimeException("err")
        val e = runCatching { uc("1") }.exceptionOrNull()
        assertTrue(e is RuntimeException)
    }

    @Test fun `invoke with different ids calls repository each time`() = runTest {
        coEvery { repo.getAppDetails(any()) } returns details
        uc("1"); uc("2")
        coVerify(exactly = 1) { repo.getAppDetails("1") }
        coVerify(exactly = 1) { repo.getAppDetails("2") }
    }
}