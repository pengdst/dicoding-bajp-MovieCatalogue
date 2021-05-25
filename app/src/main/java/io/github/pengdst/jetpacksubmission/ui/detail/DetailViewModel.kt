package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepository
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
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
    private val repository: MovieRepository
) : ViewModel() {

    private var contentId: String = ""

    fun getMovie(): LiveData<Movie> {
        val result = MutableLiveData<Movie>()
        viewModelScope.launch {
            result.postValue(repository.getMovie(contentId))
        }
        return result
    }

    fun getTvShow(): LiveData<TvShow> {
        val result = MutableLiveData<TvShow>()
        viewModelScope.launch {
            result.postValue(repository.getTv(contentId))
        }
        return result
    }

    fun setSelectedContent(contentId: String?){
        this.contentId = contentId ?: ""
    }
}