package com.app.juawcevada.vostextu.ui.posts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.app.juawcevada.vostextu.R
import com.app.juawcevada.vostextu.shared.Event
import com.app.juawcevada.vostextu.testing.SingleFragmentActivity
import com.app.juawcevada.vostextu.util.RecyclerViewMatcher
import com.app.juawcevada.vostextu.util.createTestFactory
import com.app.juawcevada.vostextu.util.post
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsFragmentTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel:PostsViewModel
    private lateinit var navigationAction: MutableLiveData<Event<PostsNavigationActions>>
    private lateinit var viewState: MutableLiveData<PostsViewState>
    private lateinit var fragment: PostsFragmentMockNavigation
    private lateinit var activityScenario: ActivityScenario<SingleFragmentActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(SingleFragmentActivity::class.java)
        navigationAction = MutableLiveData()
        viewState = MutableLiveData()
        fragment = PostsFragmentMockNavigation()

        viewModel = mock {
            on {viewState} doReturn this@PostsFragmentTest.viewState
            on {navigationAction} doReturn this@PostsFragmentTest.navigationAction
        }

        fragment.viewModelFactory = viewModel.createTestFactory()
    }

    @Test
    fun checkViewsSuccess() {
        val list = listOf(
            post {
                id { 1 }
                title { "Line" }
                body { "Triple line" }
            },
            post {
                id { 2 }
                title { "Acie" }
                body { "Acie triplici" }
            }
        )
        viewState.value = PostsViewState(
            isLoading = false,
            list = list
        )

        launchFragment()

        onView(RecyclerViewMatcher(R.id.list).atPosition(0))
            .check(matches(allOf(
                hasDescendant(withText("Line")),
                hasDescendant(withText("Triple line"))
            )))

        onView(RecyclerViewMatcher(R.id.list).atPosition(1))
            .check(matches(allOf(
                hasDescendant(withText("Acie")),
                hasDescendant(withText("Acie triplici"))
            )))
    }

    @Test
    fun checkViewsError() {
        viewState.value = PostsViewState(
            isLoading = false,
            list = emptyList(),
            errorMessage = "Error message!"
        )

        launchFragment()

        onView(withId(R.id.error_text_title))
            .check(matches(allOf(
                isDisplayed(),
                withText("Error message!")
            )))
    }

    @Test
    fun checkViewsLoading() {
        viewState.value = PostsViewState(
            isLoading = true,
            list = emptyList()
        )

        launchFragment()

        onView(withId(R.id.spin_kit)).check(matches(isDisplayed()))
    }

    @Test
    fun openDetails() {
        val item = post {
            id { 1 }
            title { "Line" }
            body { "Triple line" }
        }
        val list = listOf(item)
        viewState.value = PostsViewState(
            isLoading = false,
            list = list
        )

        navigationAction.postValue(Event(PostsNavigationActions.OpenDetail(item)))
        launchFragment()
        onView(RecyclerViewMatcher(R.id.list).atPosition(0)).perform(click())

        verify(viewModel).openDetail(item)
        val action = PostsFragmentDirections.actionPostsFragmentToPostDetailFragment(item)
        verify(fragment.navController).navigate(action)
    }


    private fun launchFragment() {
        activityScenario.onActivity {
            it.replaceFragment(fragment)
        }
    }

    class PostsFragmentMockNavigation: PostsFragment() {
        val navController = mock<NavController>()
        override fun navController() = navController
    }

}