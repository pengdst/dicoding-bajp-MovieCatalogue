package io.github.pengdst.jetpacksubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.paging.PagingData
import androidx.paging.map
import io.github.pengdst.jetpacksubmission.data.local.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.local.mapper.MovieMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.local.mapper.TvShowMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.local.mapper.TvShowMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity
import io.github.pengdst.jetpacksubmission.data.local.source.MovieLocalSource
import io.github.pengdst.jetpacksubmission.data.remote.helpers.ApiResponse
import io.github.pengdst.jetpacksubmission.data.remote.mapper.MovieMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.remote.mapper.TvMapper.toDomain
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.MovieDto
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.models.TvDto
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.response.MovieResponse
import io.github.pengdst.jetpacksubmission.data.remote.retrofit.response.TvResponse
import io.github.pengdst.jetpacksubmission.data.remote.source.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.data.remote.source.SafeRemoteSource
import io.github.pengdst.jetpacksubmission.data.utils.NetworkBoundResource
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteSource,
    private val local: MovieLocalSource
) : SafeRemoteSource(), MovieRepository {

    override fun getUpcomingMovies(): LiveData<Resource<PagingData<Movie>>> {
        return object : NetworkBoundResource<PagingData<Movie>, PagingData<MovieEntity>, MovieResponse>() {
            override suspend fun fetchFromLocal(): Flow<PagingData<MovieEntity>> {
                return local.getAllMovies().asFlow()
            }

            override fun shouldFetchFromRemote(db: PagingData<MovieEntity>?) = true

            override suspend fun fetchFromRemote(): Flow<ApiResponse<MovieResponse>?> {
                val response = remote.getUpcomingMovies()
                return flow {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(ApiResponse.success(it))
                        } ?: run {
                            emit(ApiResponse.error("Response Empty or Null", response.body()))
                        }
                    } else {
                        emit(ApiResponse.error(response.message(), response.body()))
                    }
                }.catch { e ->
                    emit(ApiResponse.error(e.message ?: "Error", response.body(), e))
                }
            }

            override fun convertToDomain(db: PagingData<MovieEntity>): PagingData<Movie> {
                return db.map { it.toDomain() }
            }

            override suspend fun saveRemoteData(remote: MovieResponse) {
                remote.results?.toDomain()?.map { it.toEntity() }?.let {
                    local.saveMovies(it)
                }
            }
        }.liveData
    }

    override fun getBookmarkedMovies() = local.getBookmarkedMovies().map { pagingData ->
        pagingData.map { it.toDomain() }
    }

    override fun getMovie(movieId: String): LiveData<Resource<Movie>> {

        return object : NetworkBoundResource<Movie, MovieEntity, MovieDto>() {
            override suspend fun fetchFromLocal(): Flow<MovieEntity> {
                return local.getMovie(movieId).asFlow()
            }

            override fun shouldFetchFromRemote(db: MovieEntity?) = true

            override suspend fun fetchFromRemote(): Flow<ApiResponse<MovieDto>?> {
                val response = remote.getMovie(movieId)
                return flow {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(ApiResponse.success(it))
                        } ?: run {
                            emit(ApiResponse.error("Response Empty or Null", response.body()))
                        }
                    } else {
                        emit(ApiResponse.error(response.message(), response.body()))
                    }
                }.catch { e ->
                    emit(ApiResponse.error(e.message ?: "Error", response.body(), e))
                }
            }

            override fun convertToDomain(db: MovieEntity): Movie {
                return db.toDomain()
            }

            override suspend fun saveRemoteData(remote: MovieDto) {
                local.saveMovie(remote.toDomain().toEntity())
            }
        }.liveData
    }

    override fun getTvOnAir(): LiveData<Resource<PagingData<TvShow>>> {

        return object : NetworkBoundResource<PagingData<TvShow>, PagingData<TvShowEntity>, TvResponse>() {
            override suspend fun fetchFromLocal(): Flow<PagingData<TvShowEntity>> {
                return local.getTvShows().asFlow()
            }

            override fun shouldFetchFromRemote(db: PagingData<TvShowEntity>?) = true

            override suspend fun fetchFromRemote(): Flow<ApiResponse<TvResponse>?> {
                val response = remote.getTvOnAir()
                return flow {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(ApiResponse.success(it))
                        } ?: run {
                            emit(ApiResponse.error("Response Empty or Null", response.body()))
                        }
                    } else {
                        emit(ApiResponse.error(response.message(), response.body()))
                    }
                }.catch { e ->
                    emit(ApiResponse.error(e.message ?: "Error", response.body(), e))
                }
            }

            override fun convertToDomain(db: PagingData<TvShowEntity>): PagingData<TvShow> {
                return db.map { it.toDomain() }
            }

            override suspend fun saveRemoteData(remote: TvResponse) {
                remote.results?.toDomain()?.map { it.toEntity() }?.let {
                    local.saveTvShows(it)
                }
            }
        }.liveData
    }

    override fun getBookmarkedTvShows() = local.getBookmarkedTvShows().map { pagingData ->
        pagingData.map { it.toDomain() }
    }

    override fun getTv(tvId: String): LiveData<Resource<TvShow>> {

        return object : NetworkBoundResource<TvShow, TvShowEntity, TvDto>() {
            override suspend fun fetchFromLocal(): Flow<TvShowEntity> {
                return local.getTv(tvId).asFlow()
            }

            override fun shouldFetchFromRemote(db: TvShowEntity?) = true

            override suspend fun fetchFromRemote(): Flow<ApiResponse<TvDto>?> {
                val response = remote.getTv(tvId)
                return flow {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            emit(ApiResponse.success(it))
                        } ?: run {
                            emit(ApiResponse.error("Response Empty or Null", response.body()))
                        }
                    } else {
                        emit(ApiResponse.error(response.message(), response.body()))
                    }
                }.catch { e ->
                    emit(ApiResponse.error(e.message ?: "Error", response.body(), e))
                }
            }

            override fun convertToDomain(db: TvShowEntity): TvShow {
                return db.toDomain()
            }

            override suspend fun saveRemoteData(remote: TvDto) {
                local.saveTvShow(remote.toDomain().toEntity())
            }
        }.liveData
    }

    override suspend fun setBookmarkedTvShow(tvShowEntity: TvShowEntity) = local.updateTvShow(tvShowEntity.apply {
        isFavourite = !isFavourite
    })

    override suspend fun setBookmarkedMovie(movieEntity: MovieEntity) = local.updateMovie(movieEntity.apply {
        isFavourite = !isFavourite
    })

}