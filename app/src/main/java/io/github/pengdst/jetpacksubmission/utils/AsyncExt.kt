package io.github.pengdst.jetpacksubmission.utils

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 5/20/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

fun <T> Call<T>.enqueueCall(
    onFailure: ((call: Call<T>, t: Throwable) -> Unit)? = null,
    onResponse: (call: Call<T>, response: Response<T>) -> Unit,
) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            onResponse(call, response)
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.e("Enque", "onFailure() called = $t")
            onFailure?.invoke(call, t)
        }
    })
}

interface LoadContent<T>{
    fun onContentReceived(courseResponses: T?)
}