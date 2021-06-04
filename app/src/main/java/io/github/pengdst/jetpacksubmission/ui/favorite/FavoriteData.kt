package io.github.pengdst.jetpacksubmission.ui.favorite

import androidx.recyclerview.widget.DiffUtil

/**
 * Created on 6/4/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
data class FavoriteData(
    val id: String,
    val title: String,
    val posterUrl: String,
    val type: String
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<FavoriteData>() {
            override fun areItemsTheSame(oldItem: FavoriteData, newItem: FavoriteData) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: FavoriteData, newItem: FavoriteData) =
                oldItem == newItem
        }
    }
}
