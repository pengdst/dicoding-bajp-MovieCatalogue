package io.github.pengdst.jetpacksubmission.data.models

import androidx.recyclerview.widget.DiffUtil

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
data class Movie(
    val id: String,
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val language: String,
    val genre: String,
    val storyLine: String,
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.title == newItem.title
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem.hashCode() == newItem.hashCode()
        }
    }
}
