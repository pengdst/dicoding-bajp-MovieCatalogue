package io.github.pengdst.jetpacksubmission.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepository
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.utils.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class FakeRepository : MovieRepository {

    companion object {
        private const val TAG = "FakeRepository"
    }

    override suspend fun getUpcomingMovies(): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()

        withContext(Dispatchers.IO) {
            try {
                val data = DataStore.moviesResponse
                result.postValue(data.map {
                    Movie(
                        id = it.id.toString(),
                        title = it.title.toString(),
                        backdropPath = it.backdropPath.toString(),
                        posterPath = it.posterPath.toString(),
                        releaseDate = it.releaseDate.toString(),
                        language = it.spokenLanguages?.map { it.englishName }.toString().replace("[", "").replace("]", ""),
                        genre = it.genres?.map { it.name }.toString().replace("[", "").replace("]", ""),
                        storyLine = it.overview.toString()
                    )
                })

            } catch (e: Exception) {
                Log.e(TAG, "getUpcomingMovies() called")
            }
        }

        return result
    }

    override suspend fun getMovie(movieId: String): LiveData<Movie> {
        val result = MutableLiveData<Movie>()

        withContext(Dispatchers.IO) {
            try {
                val data = DataStore.moviesResponse[0]
                result.postValue(
                    Movie(
                        id = data.id.toString(),
                        title = data.title.toString(),
                        backdropPath = data.backdropPath.toString(),
                        posterPath = data.posterPath.toString(),
                        releaseDate = data.releaseDate.toString(),
                        language = data.spokenLanguages?.map { it.englishName }.toString().replace("[", "").replace("]", ""),
                        genre = data.genres?.map { it.name }.toString().replace("[", "").replace("]", ""),
                        storyLine = data.overview.toString()
                    )
                )

            } catch (e: Exception) {
                Log.e(TAG, "getMovie() called")
            }
        }

        return result
    }

    override suspend fun getTvOnAir(): LiveData<List<TvShow>> {
        val result = MutableLiveData<List<TvShow>>()

        withContext(Dispatchers.IO) {
            try {
                val data = DataStore.tvShowListResponse
                result.postValue(data.map {
                    TvShow(
                        id = it.id.toString(),
                        title = it.name.toString(),
                        backdropPath = it.backdropPath.toString(),
                        posterPath = it.posterPath.toString(),
                        releaseDate = it.firstAirDate.toString(),
                        language = it.spokenLanguages?.map { it.englishName }.toString().replace("[", "").replace("]", ""),
                        genre = it.genres?.map { it.name }.toString().replace("[", "").replace("]", ""),
                        storyLine = it.overview.toString()
                    )
                })
            } catch (e: Exception) {
                Log.e(TAG, "getTvOnAir() called")
            }
        }

        return result
    }

    override suspend fun getTv(tvId: String): LiveData<TvShow> {
        val result = MutableLiveData<TvShow>()

        withContext(Dispatchers.IO) {
            try {
                val data = DataStore.tvShowListResponse[0]
                result.postValue(
                    TvShow(
                        id = data?.id.toString(),
                        title = data?.name.toString(),
                        backdropPath = data?.backdropPath.toString(),
                        posterPath = data?.posterPath.toString(),
                        releaseDate = data?.firstAirDate.toString(),
                        language = data?.spokenLanguages?.map { it.englishName }.toString().replace("[", "").replace("]", ""),
                        genre = data?.genres?.map { it.name }.toString().replace("[", "").replace("]", ""),
                        storyLine = data?.overview.toString()
                    )
                )

            } catch (e: Exception) {
                Log.e(TAG, "getTv() called")
            }
        }

        return result
    }

}