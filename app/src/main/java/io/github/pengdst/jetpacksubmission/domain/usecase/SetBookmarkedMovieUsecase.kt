package io.github.pengdst.jetpacksubmission.domain.usecase

import io.github.pengdst.jetpacksubmission.core.SuspendUseCase
import io.github.pengdst.jetpacksubmission.data.local.room.model.MovieEntity
import io.github.pengdst.jetpacksubmission.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class SetBookmarkedMovieUsecase @Inject constructor(private val repository: MovieRepository): SuspendUseCase<Unit, SetBookmarkedMovieUsecase.Params>() {
    data class Params(val movieEntity: MovieEntity)
    override suspend fun run(params: Params) = repository.setBookmarkedMovie(params.movieEntity)
}