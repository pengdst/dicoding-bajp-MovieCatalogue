package io.github.pengdst.jetpacksubmission.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

/**
 * Created on 6/3/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */

fun AppCompatActivity.findNavController(navContainerViewId: Int) =
    (supportFragmentManager.findFragmentById(navContainerViewId) as NavHostFragment)
        .findNavController()