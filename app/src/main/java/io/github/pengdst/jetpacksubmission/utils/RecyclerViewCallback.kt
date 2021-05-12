package io.github.pengdst.jetpacksubmission.utils

import android.view.View

/**
 * Created on 5/12/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
interface RecyclerViewCallback {
    interface OnItemClick<Data> {
        fun onItemClick(view: View, data: Data, position: Int)
    }
}