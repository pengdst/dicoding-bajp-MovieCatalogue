package io.github.pengdst.jetpacksubmission.domain.usecase

import androidx.paging.PagingData
import io.github.pengdst.jetpacksubmission.core.LiveDataUseCase
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class GetBookmarkedTvShowsUsecase @Inject constructor(private val repository: MovieRepository): LiveDataUseCase<PagingData<TvShow>, GetBookmarkedTvShowsUsecase.Companion>() {
    companion object;
    override fun run(params: Companion) = repository.getBookmarkedTvShows()
}