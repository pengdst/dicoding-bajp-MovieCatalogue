package io.github.pengdst.jetpacksubmission.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.data.source.domain.models.TvShow
import io.github.pengdst.jetpacksubmission.data.source.remote.MovieRemoteSource
import io.github.pengdst.jetpacksubmission.utils.LoadContent
import javax.inject.Inject

/**
 * Created on 5/19/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieRepositoryImpl @Inject constructor(
    private val remote: MovieRemoteSource
) : MovieRepository {

    override fun getUpcomingMovies(): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()

        remote.getUpcomingMovies(object : LoadContent<List<Movie>>{
            override fun onContentReceived(courseResponses: List<Movie>?) {
                courseResponses?.let {
                    result.postValue(it)
                }
            }
        })

        return result
    }

    override fun getMovie(movieId: String): LiveData<Movie> {
        val result = MutableLiveData<Movie>()

        remote.getMovie(movieId, object : LoadContent<Movie>{
            override fun onContentReceived(courseResponses: Movie?) {
                courseResponses?.let {
                    result.postValue(it)
                }
            }
        })

        return result
    }

    override fun getTvOnAir(): LiveData<List<TvShow>> {
        val result = MutableLiveData<List<TvShow>>()

        remote.getTvOnAir(object : LoadContent<List<TvShow>>{
            override fun onContentReceived(courseResponses: List<TvShow>?) {
                courseResponses?.let {
                    result.postValue(it)
                }
            }
        })

        return result
    }

    override fun getTv(tvId: String): LiveData<TvShow> {
        val result = MutableLiveData<TvShow>()

        remote.getTv(tvId, object : LoadContent<TvShow>{
            override fun onContentReceived(courseResponses: TvShow?) {
                courseResponses?.let {
                    result.postValue(it)
                }
            }
        })

        return result
    }

}