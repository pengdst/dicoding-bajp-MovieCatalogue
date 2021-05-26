package io.github.pengdst.jetpacksubmission.data.source.remote.source

import android.util.Log
import retrofit2.Response

/**
 * Created on 5/25/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
abstract class SafeRemoteSource {

    companion object {
        private const val TAG = "SafeRemoteSource"
    }

    fun <T> apiCall(response: Response<T>) : T? = try {
        if (response.isSuccessful) {
            response.body()
        } else {
            Log.e(TAG, "apiCall() called response = $response")
            null
        }

    } catch (e: Exception) {
        Log.e(TAG, "apiCall() called")
        null
    }
}
