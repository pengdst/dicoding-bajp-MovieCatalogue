package io.github.pengdst.jetpacksubmission.core

import androidx.lifecycle.LiveData
import io.github.pengdst.jetpacksubmission.data.vo.Resource

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

abstract class UseCase<Type, in Params> where Type : Any {
    abstract fun run(params: Params): LiveData<Resource<Type>>
}