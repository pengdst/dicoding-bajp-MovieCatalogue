package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.domain.models.Movie
import io.github.pengdst.jetpacksubmission.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailMovieUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetDetailTvUsecase
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
    private val getDetailTvUsecase: GetDetailTvUsecase
) : ViewModel() {

    private var contentId: String = ""

    fun getMovie(): LiveData<Resource<Movie>> {
        val result = MutableLiveData<Resource<Movie>>()
        result.postValue(Resource.Loading())
        viewModelScope.launch {
            result.postValue(getDetailMovieUsecase.run(GetDetailMovieUsecase.Params(contentId)))
        }
        return result
    }

    fun getTvShow(): LiveData<Resource<TvShow>> {
        val result = MutableLiveData<Resource<TvShow>>()
        result.postValue(Resource.Loading())
        viewModelScope.launch {
            result.postValue(getDetailTvUsecase.run(GetDetailTvUsecase.Params(contentId)))
        }
        return result
    }

    fun setSelectedContent(contentId: String?){
        this.contentId = contentId ?: ""
    }
}