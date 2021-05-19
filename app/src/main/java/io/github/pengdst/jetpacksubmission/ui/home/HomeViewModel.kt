package io.github.pengdst.jetpacksubmission.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepository
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
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    fun getMovies(): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()
        viewModelScope.launch {
            result.postValue(repository.getUpcomingMovies().value)
        }
        return result
    }
    fun getTvShowList(): LiveData<List<TvShow>> {
        val result = MutableLiveData<List<TvShow>>()
        viewModelScope.launch {
            result.postValue(repository.getTvOnAir().value)
        }
        return result
    }
}