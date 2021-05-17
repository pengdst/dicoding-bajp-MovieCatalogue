package io.github.pengdst.jetpacksubmission.ui.home

import androidx.lifecycle.ViewModel
import io.github.pengdst.jetpacksubmission.utils.DataStore

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class HomeViewModel : ViewModel() {
    fun getMovies() = DataStore.movies
    fun getTvShowList() = DataStore.tvShowList
}