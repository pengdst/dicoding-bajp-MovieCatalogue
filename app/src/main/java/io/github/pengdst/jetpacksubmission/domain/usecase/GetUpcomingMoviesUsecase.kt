package io.github.pengdst.jetpacksubmission.domain.usecase

import io.github.pengdst.jetpacksubmission.core.UseCase
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class GetUpcomingMoviesUsecase @Inject constructor(private val repository: MovieRepository): UseCase<List<Movie>, GetUpcomingMoviesUsecase.Companion>() {
    companion object;
    override suspend fun run(params: Companion) = repository.getUpcomingMovies()
}