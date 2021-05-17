package io.github.pengdst.jetpacksubmission.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.TabViewActions.selectTabAtPosition
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

    private val dummyMovie = DataStore.movies
    private val dummyTvShow = DataStore.tvShowList

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        moveTabToPosition(R.id.tabs, 0)
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

        checkContent(R.id.tv_title, dummyMovie[0].title)
        checkContent(R.id.tv_release_date, dummyMovie[0].releaseDate)
        checkContent(R.id.tv_genre, dummyMovie[0].genre)
        checkContent(R.id.tv_storyline, dummyMovie[0].storyLine)
        checkContent(R.id.tv_language, dummyMovie[0].language)
    }

    @Test
    fun loadTvShow() {
        moveTabToPosition(R.id.tabs, 1)
        checkDisplayed(R.id.rv_tv_shows)
        checkScrollToPosition(R.id.rv_tv_shows, dummyTvShow.size)
    }

    @Test
    fun loadDetailTvShow() {
        loadTvShow()
        performRecyclerViewClick(R.id.rv_tv_shows, 0)

        checkDisplayed(R.id.iv_backdrop)
        Espresso.onView(ViewMatchers.withId(R.id.iv_backdrop)).perform(ViewActions.swipeUp())

        checkDisplayed(R.id.iv_thumbnail)
        checkDisplayed(R.id.tv_title)
        checkDisplayed(R.id.tv_release_date)
        checkDisplayed(R.id.tv_genre)
        checkDisplayed(R.id.tv_storyline)
        checkDisplayed(R.id.tv_language)

        checkContent(R.id.tv_title, dummyTvShow[0].title)
        checkContent(R.id.tv_release_date, dummyTvShow[0].releaseDate)
        checkContent(R.id.tv_genre, dummyTvShow[0].genre)
        checkContent(R.id.tv_storyline, dummyTvShow[0].storyLine)
        checkContent(R.id.tv_language, dummyTvShow[0].language)
    }

    private fun moveTabToPosition(tabs: Int, tabIndex: Int) {
        Espresso.onView(ViewMatchers.withId(tabs)).perform(selectTabAtPosition(tabIndex))
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

    private fun checkContent(resId: Int, content: String) {
        Espresso.onView(ViewMatchers.withId(resId))
            .check(ViewAssertions.matches(ViewMatchers.withText(content)))
    }
}