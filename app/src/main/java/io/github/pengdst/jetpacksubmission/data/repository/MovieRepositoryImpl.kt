package io.github.pengdst.jetpacksubmission.data.repository

import io.github.pengdst.jetpacksubmission.data.source.remote.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.data.source.remote.SafeRemoteSource
import io.github.pengdst.jetpacksubmission.data.source.remote.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.source.remote.mapper.TvMapper.toDomain
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

    override suspend fun getUpcomingMovies() = apiCall(remote.getUpcomingMovies())?.results?.toDomain()
    override suspend fun getMovie(movieId: String) = remote.getMovie(movieId).body()?.toDomain()
    override suspend fun getTvOnAir() = apiCall(remote.getTvOnAir())?.results?.toDomain()
    override suspend fun getTv(tvId: String) = apiCall(remote.getTv(tvId))?.toDomain()

}