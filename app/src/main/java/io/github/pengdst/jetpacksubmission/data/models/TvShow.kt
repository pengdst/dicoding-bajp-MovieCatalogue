package io.github.pengdst.jetpacksubmission.data.models

import androidx.recyclerview.widget.DiffUtil

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
data class TvShow(
    val title: String,
    val imageUrl: String,
    val releaseDate: String,
    val language: String,
    val genre: String,
    val storyLine: String,
){
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow) = oldItem.title == newItem.title
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow) = oldItem.hashCode() == newItem.hashCode()
        }
    }
}
