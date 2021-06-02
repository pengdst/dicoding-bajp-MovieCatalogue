package io.github.pengdst.jetpacksubmission.core

import androidx.lifecycle.LiveData

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

abstract class UseCase<Type : Any, in Params> : AbstractUseCase<Type, Params> {
    abstract fun run(params: Params): Type
}