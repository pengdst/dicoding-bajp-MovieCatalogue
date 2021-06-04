package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.local.mapper.MovieEntityMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.local.mapper.TvShowEntityMapper.toEntity
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailTvUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.SetBookmarkedMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.SetBookmarkedTvShowUsecase
import kotlinx.coroutines.launch
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
    private val getDetailTvUsecase: GetDetailTvUsecase,
    private val setBookmarkedMovieUsecase: SetBookmarkedMovieUsecase,
    private val setBookmarkedTvShowUsecase: SetBookmarkedTvShowUsecase,
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

    fun setBookmark(movie: Movie) {
        viewModelScope.launch {
            setBookmarkedMovieUsecase.run(SetBookmarkedMovieUsecase.Params(movie.toEntity()))
        }
    }

    fun setBookmark(tvShow: TvShow) {
        viewModelScope.launch {
            setBookmarkedTvShowUsecase.run(SetBookmarkedTvShowUsecase.Params(tvShow.toEntity()))
        }
    }
}