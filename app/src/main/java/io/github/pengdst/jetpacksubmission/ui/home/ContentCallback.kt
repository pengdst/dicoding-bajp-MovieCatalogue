package io.github.pengdst.jetpacksubmission.ui.home

/**
 * Created on 5/16/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
interface ContentCallback {
    fun moveTo(position: Int, contentId: String, contentType: String)
}