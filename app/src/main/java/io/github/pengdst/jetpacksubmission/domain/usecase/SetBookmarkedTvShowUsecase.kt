package io.github.pengdst.jetpacksubmission.domain.usecase

import io.github.pengdst.jetpacksubmission.core.SuspendUseCase
import io.github.pengdst.jetpacksubmission.data.local.room.model.TvShowEntity
import io.github.pengdst.jetpacksubmission.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class SetBookmarkedTvShowUsecase @Inject constructor(private val repository: MovieRepository): SuspendUseCase<Unit, SetBookmarkedTvShowUsecase.Params>() {
    data class Params(val tvShowEntity: TvShowEntity)
    override suspend fun run(params: Params) = repository.setBookmarkedTvShow(params.tvShowEntity)
}