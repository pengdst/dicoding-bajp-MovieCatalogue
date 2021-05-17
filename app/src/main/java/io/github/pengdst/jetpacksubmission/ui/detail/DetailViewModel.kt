package io.github.pengdst.jetpacksubmission.ui.detail

import androidx.lifecycle.ViewModel
import io.github.pengdst.jetpacksubmission.data.models.Movie
import io.github.pengdst.jetpacksubmission.data.models.TvShow
import io.github.pengdst.jetpacksubmission.utils.DataStore

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class DetailViewModel : ViewModel() {

    private var contentPosition = 0

    fun getMovies() = DataStore.movies
    fun getTvShowList() = DataStore.tvShowList

    fun getMovie(): Movie {
        return DataStore.movies[contentPosition]
    }

    fun getTvShow(): TvShow {
        return DataStore.tvShowList[contentPosition]
    }

    fun setSelectedContent(contentPosition: Int?){
        this.contentPosition = contentPosition ?: 0
    }
}