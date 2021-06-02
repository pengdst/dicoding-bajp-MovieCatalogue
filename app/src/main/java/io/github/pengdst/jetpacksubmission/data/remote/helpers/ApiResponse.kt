package io.github.pengdst.jetpacksubmission.data.remote.helpers

/**
 * Created on 5/28/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class ApiResponse<T>(val status: StatusResponse, val body: T?, val message: String?, val throwable: Throwable? = null) {

    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> empty(msg: String, body: T): ApiResponse<T> = ApiResponse(StatusResponse.EMPTY, body, msg)

        fun <T> error(msg: String, body: T?, throwable: Throwable? = null): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, body, msg, throwable)
    }

}