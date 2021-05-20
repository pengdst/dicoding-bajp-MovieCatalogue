package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.data.repository.MovieRepository
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

    fun getMovie() = repository.getMovie(contentId)

    fun getTvShow() = repository.getTv(contentId)

    fun setSelectedContent(contentId: String?){
        this.contentId = contentId ?: ""
    }
}