package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailTvUsecase
import javax.inject.Inject

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailMovieUsecase: GetDetailMovieUsecase,
    private val getDetailTvUsecase: GetDetailTvUsecase
) : ViewModel() {

    private var contentId: String = ""

    fun getMovie(): LiveData<Resource<Movie>> {
        return getDetailMovieUsecase.run(GetDetailMovieUsecase.Params(contentId))
    }

    fun getTvShow(): LiveData<Resource<TvShow>> {
        return getDetailTvUsecase.run(GetDetailTvUsecase.Params(contentId))
    }

    fun setSelectedContent(contentId: String?){
        this.contentId = contentId ?: ""
    }
}