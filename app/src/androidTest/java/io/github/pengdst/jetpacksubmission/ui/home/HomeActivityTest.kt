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
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
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

    private val dummyMovie = DataStore.moviesResponse.map { data ->
        Movie(
            id = data.id.toString(),
            title = data.title.toString(),
            backdropPath = data.backdropPath.toString(),
            posterPath = data.posterPath.toString(),
            releaseDate = data.releaseDate.toString(),
            language = data.spokenLanguages?.map { it.englishName }.toString()
                .replace("[", "").replace("]", ""),
            genre = data.genres?.map { it.name }.toString().replace("[", "")
                .replace("]", ""),
            storyLine = data.overview.toString()
        )
    }
    private val dummyTvShow = DataStore.tvShowListResponse.map {data->
        TvShow(
            id = data.id.toString(),
            title = data.name.toString(),
            backdropPath = data.backdropPath.toString(),
            posterPath = data.posterPath.toString(),
            releaseDate = data.firstAirDate.toString(),
            language = data.spokenLanguages?.map { it.englishName }.toString()
                .replace("[", "").replace("]", ""),
            genre = data.genres?.map { it.name }.toString().replace("[", "")
                .replace("]", ""),
            storyLine = data.overview.toString()
        )
    }

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

        checkContent(R.id.tv_title, "Godzilla vs. Kong")
        checkContent(R.id.tv_release_date, "2021-03-24")
        checkContent(R.id.tv_genre, "Science Fiction, Action, Drama")
        checkContent(R.id.tv_language, "English, Turkish")
        checkContent(R.id.tv_storyline, "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.")
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

        checkContent(R.id.tv_title, "The Flash")
        checkContent(R.id.tv_release_date, "2014-10-07")
        checkContent(R.id.tv_genre, "Drama, Sci-Fi & Fantasy")
        checkContent(R.id.tv_language, "English")
        checkContent(R.id.tv_storyline, "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.")
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