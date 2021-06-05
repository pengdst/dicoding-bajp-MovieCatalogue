package io.github.pengdst.jetpacksubmission.utils

import android.content.Context
import android.widget.Toast

/**
 * Created on 6/1/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

@Suppress("unused")
fun Context?.shortToast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
fun Context?.longToast(message: String?) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()