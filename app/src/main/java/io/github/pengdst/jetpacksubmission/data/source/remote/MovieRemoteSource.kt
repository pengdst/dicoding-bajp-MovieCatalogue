package io.github.pengdst.jetpacksubmission.data.source.remote

import android.util.Log
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.source.remote.routes.MovieRoute
import io.github.pengdst.jetpacksubmission.utils.LoadContent
import io.github.pengdst.jetpacksubmission.utils.enqueueCall

/**
 * Created on 5/20/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRemoteSource(
    private val movieRoute: MovieRoute
) {

    companion object {
        private const val TAG = "MovieRemoteSource"
    }

    fun getUpcomingMovies(callback: LoadContent<List<Movie>>) {
        movieRoute.getUpcomingMovies().enqueueCall { _, response ->
            if (response.isSuccessful) {
                val results = response.body()?.results ?: emptyList()
                callback.onContentReceived(results.map { data ->
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
                })
            } else {
                Log.e(TAG, "getUpcomingMovies() called response = $response")
            }
        }
    }

    fun getMovie(movieId: String, callback: LoadContent<Movie>) {
        movieRoute.getMovie(movieId).enqueueCall { _, response ->
            if (response.isSuccessful) {
                val data = response.body()
                callback.onContentReceived(
                    Movie(
                        id = data?.id.toString(),
                        title = data?.title.toString(),
                        backdropPath = data?.backdropPath.toString(),
                        posterPath = data?.posterPath.toString(),
                        releaseDate = data?.releaseDate.toString(),
                        language = data?.spokenLanguages?.map { it.englishName }.toString()
                            .replace("[", "").replace("]", ""),
                        genre = data?.genres?.map { it.name }.toString().replace("[", "")
                            .replace("]", ""),
                        storyLine = data?.overview.toString()
                    )
                )
            } else {
                Log.e(TAG, "getMovie() called response = $response")
            }
        }
    }

    fun getTvOnAir(callback: LoadContent<List<TvShow>>) {
        movieRoute.getTvOnAir().enqueueCall { _, response ->
            if (response.isSuccessful) {
                val results = response.body()?.results ?: emptyList()
                callback.onContentReceived(results.map { data ->
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
                })
            } else {
                Log.e(TAG, "getTvOnAir() called response = $response")
            }
        }
    }

    fun getTv(tvId: String, callback: LoadContent<TvShow>) {
        movieRoute.getTv(tvId).enqueueCall { _, response ->
            if (response.isSuccessful) {
                val data = response.body()
                callback.onContentReceived(
                    TvShow(
                        id = data?.id.toString(),
                        title = data?.name.toString(),
                        backdropPath = data?.backdropPath.toString(),
                        posterPath = data?.posterPath.toString(),
                        releaseDate = data?.firstAirDate.toString(),
                        language = data?.spokenLanguages?.map { it.englishName }.toString()
                            .replace("[", "").replace("]", ""),
                        genre = data?.genres?.map { it.name }.toString().replace("[", "")
                            .replace("]", ""),
                        storyLine = data?.overview.toString()
                    )
                )
            } else {
                Log.e(TAG, "getTv() called response = $response")
            }
        }
    }
}