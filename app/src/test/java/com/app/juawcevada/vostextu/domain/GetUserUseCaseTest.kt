package com.app.juawcevada.vostextu.domain

import arrow.core.Try
import arrow.core.orNull
import com.app.juawcevada.vostextu.data.UsersRepository
import com.app.juawcevada.vostextu.shared.AppDispatchers
import com.app.juawcevada.vostextu.util.user
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetUserUseCaseTest {

    private val appDispatchers = AppDispatchers(
        Dispatchers.Unconfined,
        Dispatchers.Unconfined,
        Dispatchers.Unconfined
    )

    @Test
    fun `get user from a single item list`() = runBlocking {
        val usersRepository: UsersRepository = mock {
            onBlocking { getUsers() } doReturn Try.just(listOf(
                user { id { 0 } }
            ))
        }

        val useCase = GetUserUseCase(appDispatchers, usersRepository)

        assertEquals(Try.just(user { id { 0 } }), useCase(0))
    }

    @Test
    fun `get user from a three item list`()  = runBlocking {
        val usersRepository: UsersRepository = mock {
            onBlocking { getUsers() } doReturn Try.just(listOf(
                user { id { 0 } },
                user { id { 3 } },
                user { id { 5 } }
            ))
        }

        val useCase = GetUserUseCase(appDispatchers, usersRepository)

        assertEquals(Try.just(user { id { 3 } }), useCase(3))
    }
    @Test
    fun `get user from a empty list`() = runBlocking {
        val usersRepository: UsersRepository = mock {
            onBlocking { getUsers() } doReturn Try.just(emptyList())

        }

        val useCase = GetUserUseCase(appDispatchers, usersRepository)

        assertEquals(NoSuchElementException::class.java, useCase(1).failed().orNull()!!.javaClass)
    }

    @Test
    fun `get user from a list without the user id`()  = runBlocking {
        val usersRepository: UsersRepository = mock {
            onBlocking { getUsers() } doReturn Try.just(listOf(
                user { id { 0 } },
                user { id { 3 } },
                user { id { 5 } }
            ))
        }

        val useCase = GetUserUseCase(appDispatchers, usersRepository)

        assertEquals(NoSuchElementException::class.java, useCase(1).failed().orNull()!!.javaClass)
    }

    @Test
    fun `get user and get a remote error`()  = runBlocking {
        val usersRepository: UsersRepository = mock {
            onBlocking { getUsers() } doReturn Try.raise(Exception())
        }

        val useCase = GetUserUseCase(appDispatchers, usersRepository)

        assertEquals(Exception::class.java, useCase(1).failed().orNull()!!.javaClass)
    }
}