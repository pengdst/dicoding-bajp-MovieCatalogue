package io.github.pengdst.jetpacksubmission.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.pengdst.jetpacksubmission.domain.usecase.GetBookmarkedMoviesUsecase
import io.github.pengdst.jetpacksubmission.domain.usecase.GetBookmarkedTvShowsUsecase
import io.github.pengdst.jetpacksubmission.utils.DataStore
import javax.inject.Inject

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getBookmarkedMoviesUsecase: GetBookmarkedMoviesUsecase,
    private val getBookmarkedTvShowsUsecase: GetBookmarkedTvShowsUsecase
) : ViewModel() {

    fun getMovies() = getBookmarkedMoviesUsecase.run(GetBookmarkedMoviesUsecase.Companion)

    fun getTvShowList() = getBookmarkedTvShowsUsecase.run(GetBookmarkedTvShowsUsecase.Companion)

    fun getFavoriteData(type: String) = when (type) {
        DataStore.TYPE_MOVIE -> getMovies().map { list ->
            list.map {
                FavoriteData(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.imagePosterUrl,
                    type = DataStore.TYPE_MOVIE
                )
            }
        }
        else -> getTvShowList().map { list ->
            list.map {
                FavoriteData(
                    id = it.id,
                    title = it.title,
                    posterUrl = it.imagePosterUrl,
                    type = DataStore.TYPE_TV_SHOW
                )
            }
        }
    }

}