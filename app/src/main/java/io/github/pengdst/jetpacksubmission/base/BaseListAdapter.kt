package io.github.pengdst.jetpacksubmission.base

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.pengdst.jetpacksubmission.data.models.Movie
import io.github.pengdst.jetpacksubmission.utils.RecyclerViewCallback

/**
 * Created on 5/12/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
abstract class BaseListAdapter<Data, ViewHolder : RecyclerView.ViewHolder>(differ: DiffUtil.ItemCallback<Data>) : ListAdapter<Data, ViewHolder>(differ) {

    protected var itemClickCallback: RecyclerViewCallback.OnItemClick<Movie>? = null

    fun setOnItemClickListener(onClick: (View, Movie, Int)->Unit){
        itemClickCallback = object : RecyclerViewCallback.OnItemClick<Movie>{
            override fun onItemClick(view: View, data: Movie, position: Int) {
                onClick(view, data, position)
            }
        }
    }
}