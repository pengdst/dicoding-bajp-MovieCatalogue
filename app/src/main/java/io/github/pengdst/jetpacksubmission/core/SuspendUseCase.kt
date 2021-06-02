package io.github.pengdst.jetpacksubmission.core

/**
 * Created on 6/2/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

abstract class SuspendUseCase<Type : Any, in Params> : AbstractUseCase<Type, Params> {
    abstract suspend fun run(params: Params): Type
}