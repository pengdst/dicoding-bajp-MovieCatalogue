package io.github.pengdst.jetpacksubmission.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.data.source.remote.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.source.remote.mapper.TvMapper.toDomain
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created on 5/17/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    private val dummyMovie = DataStore.moviesResponse.toDomain()
    private val dummyTvShow = DataStore.tvShowListResponse.toDomain()

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResourceForMainActivity())
    }

    @Test
    fun loadMovie() {
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeRight())
        checkDisplayed(R.id.rv_movies)
        checkScrollToPosition(R.id.rv_movies, dummyMovie.size)
    }

    @Test
    fun loadDetailMovie() {
        loadMovie()
        performRecyclerViewClick(R.id.rv_movies, 0)

        checkDisplayed(R.id.iv_backdrop)
        Espresso.onView(ViewMatchers.withId(R.id.iv_backdrop)).perform(ViewActions.swipeUp())

        checkDisplayed(R.id.iv_thumbnail)
        checkDisplayed(R.id.tv_title)
        checkDisplayed(R.id.tv_release_date)
        checkDisplayed(R.id.tv_genre)
        checkDisplayed(R.id.tv_storyline)
        checkDisplayed(R.id.tv_language)
    }

    @Test
    fun loadTvShow() {
        Espresso.onView(ViewMatchers.withId(R.id.view_pager)).perform(ViewActions.swipeLeft())
        checkDisplayed(R.id.rv_tv_shows)
        checkScrollToPosition(R.id.rv_tv_shows, dummyTvShow.size)
    }

    @Test
    fun loadDetailTvShow() {
        loadTvShow()
        Thread.sleep(1000)
        performRecyclerViewClick(R.id.rv_tv_shows, 0)
        Thread.sleep(1000)

        checkDisplayed(R.id.iv_backdrop)
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withId(R.id.iv_backdrop)).perform(ViewActions.swipeUp())
        Thread.sleep(1000)

        checkDisplayed(R.id.iv_thumbnail)
        checkDisplayed(R.id.tv_title)
        checkDisplayed(R.id.tv_release_date)
        checkDisplayed(R.id.tv_genre)
        checkDisplayed(R.id.tv_storyline)
        checkDisplayed(R.id.tv_language)
    }

    private fun checkScrollToPosition(resId: Int, size: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(size))
    }

    private fun performRecyclerViewClick(resId: Int, position: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
    }

    private fun checkDisplayed(resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(isDisplayed()))
    }

}