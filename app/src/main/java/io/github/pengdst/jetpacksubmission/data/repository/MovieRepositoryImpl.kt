package io.github.pengdst.jetpacksubmission.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.source.remote.routes.MovieRoute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRepositoryImpl @Inject constructor(
    private val movieRoute: MovieRoute
) : MovieRepository {

    companion object {
        private const val TAG = "MovieRepositoryImpl"
    }

    override suspend fun getUpcomingMovies(): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()

        withContext(Dispatchers.IO) {
            try {
                val response = movieRoute.getUpcomingMovies()
                if (response.isSuccessful){
                    val data = response.body()?.results ?: emptyList()
                    result.postValue(data.map {
                        Movie(
                            id = it.id.toString(),
                            title = it.title.toString(),
                            backdropPath = it.backdropPath.toString(),
                            posterPath = it.posterPath.toString(),
                            releaseDate = it.releaseDate.toString(),
                            language = it.originalLanguage.toString(),
                            genre = it.genreIds.toString(),
                            storyLine = it.overview.toString()
                        )
                    })
                }else {
                    Log.e(TAG, "getUpcomingMovies() called response = $response")
                }

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
                val response = movieRoute.getMovie(movieId)
                if (response.isSuccessful){
                    val data = response.body()
                    result.postValue(
                        Movie(
                            id = data?.id.toString(),
                            title = data?.title.toString(),
                            backdropPath = data?.backdropPath.toString(),
                            posterPath = data?.posterPath.toString(),
                            releaseDate = data?.releaseDate.toString(),
                            language = data?.originalLanguage.toString(),
                            genre = data?.genreIds.toString(),
                            storyLine = data?.overview.toString()
                        ))
                }else {
                    Log.e(TAG, "getMovie() called response = $response")
                }

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
                val response = movieRoute.getTvOnAir()
                if (response.isSuccessful){
                    val data = response.body()?.results ?: emptyList()
                    result.postValue(data.map {
                        TvShow(
                            id = it.id.toString(),
                            title = it.name.toString(),
                            backdropPath = it.backdropPath.toString(),
                            posterPath = it.posterPath.toString(),
                            releaseDate = it.firstAirDate.toString(),
                            language = it.originalLanguage.toString(),
                            genre = it.genreIds.toString(),
                            storyLine = it.overview.toString()
                        )
                    })
                }else {
                    Log.e(TAG, "getTvOnAir() called response = $response")
                }

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
                val response = movieRoute.getTv(tvId)
                if (response.isSuccessful){
                    val data = response.body()
                    result.postValue(
                        TvShow(
                            id = data?.id.toString(),
                            title = data?.name.toString(),
                            backdropPath = data?.backdropPath.toString(),
                            posterPath = data?.posterPath.toString(),
                            releaseDate = data?.firstAirDate.toString(),
                            language = data?.originalLanguage.toString(),
                            genre = data?.genreIds.toString(),
                            storyLine = data?.overview.toString()
                        )
                    )
                }else {
                    Log.e(TAG, "getTv() called response = $response")
                }

            } catch (e: Exception) {
                Log.e(TAG, "getTv() called")
            }
        }

        return result
    }

}