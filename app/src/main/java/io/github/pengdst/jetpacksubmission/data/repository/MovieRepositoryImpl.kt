package io.github.pengdst.jetpacksubmission.data.repository

import android.util.Log
import io.github.pengdst.jetpacksubmission.data.remote.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.remote.mapper.TvMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.remote.source.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.data.remote.source.SafeRemoteSource
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteSource
) : SafeRemoteSource(), MovieRepository {

    companion object {
        private const val TAG = "MovieRepositoryImpl"
    }

    override suspend fun getUpcomingMovies(): Resource<List<Movie>> {
        val response = remote.getUpcomingMovies()

        return if (response.isSuccessful) {
            response.body()?.results?.toDomain()?.let {
                Resource.Success(it, response.message().toString())
            } ?: Resource.Error(response.body()?.results?.toDomain(), "Resource Null or Empty")
        } else {
            Log.e(TAG, "apiCall() called response = $response")
            Resource.Error(response.body()?.results?.toDomain(), response.message())
        }
    }
    override suspend fun getMovie(movieId: String): Resource<Movie> {
        val response = remote.getMovie(movieId)

        return if (response.isSuccessful) {
            response.body()?.toDomain()?.let {
                Resource.Success(it, response.message().toString())
            } ?: Resource.Error(response.body()?.toDomain(), "Resource Null or Empty")
        } else {
            Log.e(TAG, "apiCall() called response = $response")
            Resource.Error(response.body()?.toDomain(), response.message())
        }
    }
    override suspend fun getTvOnAir(): Resource<List<TvShow>> {
        val response = remote.getTvOnAir()

        return if (response.isSuccessful) {
            response.body()?.results?.toDomain()?.let {
                Resource.Success(it, response.message().toString())
            } ?: Resource.Error(response.body()?.results?.toDomain(), "Resource Null or Empty")
        } else {
            Log.e(TAG, "apiCall() called response = $response")
            Resource.Error(response.body()?.results?.toDomain(), response.message())
        }
    }
    override suspend fun getTv(tvId: String): Resource<TvShow> {
        val response = remote.getTv(tvId)

        return if (response.isSuccessful) {
            response.body()?.toDomain()?.let {
                Resource.Success(it, response.message().toString())
            } ?: Resource.Error(response.body()?.toDomain(), "Resource Null or Empty")
        } else {
            Log.e(TAG, "apiCall() called response = $response")
            Resource.Error(response.body()?.toDomain(), response.message())
        }
    }

}