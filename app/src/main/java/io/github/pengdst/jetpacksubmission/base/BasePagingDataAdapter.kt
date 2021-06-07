package io.github.pengdst.jetpacksubmission.base

import android.view.View
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.pengdst.jetpacksubmission.utils.RecyclerViewCallback

/**
 * Created on 5/12/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
abstract class BasePagingDataAdapter<Data : Any, ViewHolder : RecyclerView.ViewHolder>(differ: DiffUtil.ItemCallback<Data>) :
    PagingDataAdapter<Data, ViewHolder>(differ) {

    protected var itemClickCallback: RecyclerViewCallback.OnItemClick<Data>? = null

    fun setOnItemClickListener(onClick: (View, Data, Int) -> Unit) {
        itemClickCallback = object : RecyclerViewCallback.OnItemClick<Data> {
            override fun onItemClick(view: View, data: Data, position: Int) {
                onClick(view, data, position)
            }
        }
    }
}