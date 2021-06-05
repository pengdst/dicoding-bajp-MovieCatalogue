package io.github.pengdst.jetpacksubmission.data.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import io.github.pengdst.jetpacksubmission.data.remote.helpers.ApiResponse
import io.github.pengdst.jetpacksubmission.data.remote.helpers.StatusResponse
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import kotlinx.coroutines.flow.*

/**
 * Created on 5/28/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

abstract class NetworkBoundResource<Domain, Db, Remote> {

    companion object {
        private const val TAG = "NetworkBoundResource"
    }

    abstract suspend fun fetchFromLocal(): Flow<Db>
    abstract fun shouldFetchFromRemote(db: Db?): Boolean
    abstract suspend fun fetchFromRemote(): Flow<ApiResponse<Remote>?>
    @Suppress("MemberVisibilityCanBePrivate")
    fun processRemoteResponse(response: ApiResponse<Remote>) {}
    abstract fun convertToDomain(db: Db): Domain
    abstract suspend fun saveRemoteData(remote: Remote)
    @Suppress("MemberVisibilityCanBePrivate")
    fun onFetchFailed(errorBody: String?, throwable: Throwable? = null) {
        Log.e(TAG, "onFetchFailed: ", throwable)
    }

    private val flow: Flow<Resource<Domain>> = flow {

        val localData = fetchFromLocal().first()

        if (shouldFetchFromRemote(localData)) {
            emit(Resource.Loading(convertToDomain(localData)))
            fetchFromRemote().collect { apiResponse ->
                when (apiResponse?.status) {
                    StatusResponse.SUCCESS -> {
                        processRemoteResponse(apiResponse)
                        apiResponse.body?.let { saveRemoteData(it) }
                        emitAll(fetchFromLocal().map { dbData ->
                            Resource.Success(convertToDomain(dbData), apiResponse.message ?: "Success!")
                        })
                    }

                    StatusResponse.ERROR -> {
                        onFetchFailed(apiResponse.message)
                        emitAll(fetchFromLocal().map {
                            Resource.Error(
                                data = convertToDomain(it),
                                message = apiResponse.message
                            )
                        })
                    }
                }
            }
        } else {
            emitAll(fetchFromLocal().map { Resource.Success(convertToDomain(it)) })
        }
    }.onStart { emit(Resource.Loading(null)) }.catch { e ->
        e.printStackTrace()
        Log.e(TAG, "catch: ", e)
        emit(Resource.Error(throwable = e, message = e.message, data = null))
    }

    val liveData: LiveData<Resource<Domain>> = flow.asLiveData()
}