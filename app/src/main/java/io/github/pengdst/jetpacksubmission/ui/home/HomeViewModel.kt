package io.github.pengdst.jetpacksubmission.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetTvOnAirUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetUpcomingMoviesUsecase
import javax.inject.Inject

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUpcomingMoviesUsecase: GetUpcomingMoviesUsecase,
    private val getTvOnAirUsecase: GetTvOnAirUsecase
) : ViewModel() {

    fun getMovies(): LiveData<Resource<List<Movie>>> {
        return getUpcomingMoviesUsecase.run(GetUpcomingMoviesUsecase.Companion)
    }

    fun getTvShowList(): LiveData<Resource<List<TvShow>>> {
        return getTvOnAirUsecase.run(GetTvOnAirUsecase.Companion)
    }

}