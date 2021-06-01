package io.github.pengdst.jetpacksubmission.data.vo

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
sealed class Resource<T> {
    class Success<T>(val data: T,val  message: String): Resource<T>()
    class Error<T>(val data: T?, val message: String, val throwable: Throwable? = null): Resource<T>()
    object Loading: Resource<Any>()
}
