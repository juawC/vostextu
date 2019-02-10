package com.app.juawcevada.vostextu.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import arrow.core.Try
import com.app.juawcevada.vostextu.model.PostEntity
import com.app.juawcevada.vostextu.shared.AppDispatchers
import com.app.juawcevada.vostextu.util.post
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val unconfineDispatchers =
        AppDispatchers(
            Dispatchers.Unconfined,
            Dispatchers.Unconfined,
            Dispatchers.Unconfined
        )

    private val defaultDispatchers =
        AppDispatchers(
            Dispatchers.Default,
            Dispatchers.Default,
            Dispatchers.Default
        )

    @Test
    fun `show empty list`() {
        val viewModel = viewModel {
            posts { Try.just(emptyList()) }
        }

        with(viewModel.viewState.value!!) {
            assertEquals(emptyList<PostEntity>(), list)
            assertEquals(false, isLoading)
            assertNull(errorMessage)
        }
        with(viewModel.navigationAction.value) {
            assertNull(this)
        }
    }

    @Test
    fun `show not empty list`() {
        val postList = listOf(
            post {
                id { 0 }
            },
            post {
                id { 1 }
            },
            post {
                id { 2 }
            },
            post {
                id { 3 }
            }
        )
        val viewModel = viewModel {
            posts { Try.just(postList) }
        }

        with(viewModel.viewState.value!!) {
            assertEquals(postList, list)
            assertEquals(false, isLoading)
            assertNull(errorMessage)
        }
        with(viewModel.navigationAction.value) {
            assertNull(this)
        }
    }

    @Test
    fun `show error fetching list`() {
        val viewModel = viewModel {
            posts { Try.raise(Exception()) }
        }

        with(viewModel.viewState.value!!) {
            assertEquals(emptyList<List<PostEntity>>(), list)
            assertEquals(false, isLoading)
            assertNotNull(errorMessage)
        }
        with(viewModel.navigationAction.value) {
            assertNull(this)
        }
    }

    @Test
    fun `open list detail`() {
        val postList = listOf(
            post {
                id { 0 }
            },
            post {
                id { 1 }
            },
            post {
                id { 2 }
            },
            post {
                id { 3 }
            }
        )
        val viewModel = viewModel {
            posts { Try.just(postList) }

        }
        viewModel.openDetail(post { id { 2 } })

        with(viewModel.viewState.value!!) {
            assertEquals(postList, list)
            assertEquals(false, isLoading)
            assertNull(errorMessage)
        }
        with(viewModel.navigationAction.value!!) {
            assertEquals(PostsNavigationActions.OpenDetail(post { id { 2 } }), getContentIfNotHandled())
        }
    }

    @Test
    fun `show loading`() {
        val viewModel = viewModel {
            posts { Try.just(emptyList()) }
            dispatchers { defaultDispatchers }
        }

        with(viewModel.viewState.value!!) {
            assertEquals(emptyList<PostEntity>(), list)
            assertEquals(true, isLoading)
            assertNull(errorMessage)
        }
        with(viewModel.navigationAction.value) {
            assertNull(this)
        }
    }

    private inner class ViewModelBuilder {
        private var dispatchers: AppDispatchers = unconfineDispatchers
        private var posts: Try<List<PostEntity>> = Try.just(emptyList())

        fun posts(body: () -> Try<List<PostEntity>>) {
            posts = body()
        }

        fun dispatchers(body: () -> AppDispatchers) {
            dispatchers = body()
        }


        fun build() = PostsViewModel(dispatchers, mock {
            onBlocking { invoke(Unit) } doReturn posts
        })
    }

    private fun viewModel(body: ViewModelBuilder.() -> Unit) = ViewModelBuilder().apply(body).build()
}