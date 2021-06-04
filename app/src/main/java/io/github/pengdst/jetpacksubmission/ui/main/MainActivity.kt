package io.github.pengdst.jetpacksubmission.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.core.HasToolbarFragment
import io.github.pengdst.jetpacksubmission.databinding.ActivityMainBinding
import io.github.pengdst.jetpacksubmission.ui.detail.DetailActivity
import io.github.pengdst.jetpacksubmission.ui.home.ContentCallback
import io.github.pengdst.jetpacksubmission.utils.findNavController
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ContentCallback {

    private lateinit var navController: NavController
    val binding: ActivityMainBinding by viewBindings()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        navController = findNavController(R.id.fragment_container_view)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.isVisible = destination is HasToolbarFragment
            if (destination is HasToolbarFragment) setSupportActionBar(destination.toolbar)
        }
    }

    override fun moveTo(position: Int, contentId: String, contentType: String) {
        Intent(applicationContext, DetailActivity::class.java).apply {
            putExtras(Bundle().apply {
                putInt(DetailActivity.EXTRA_CONTENT_POSITION, position)
                putString(DetailActivity.EXTRA_CONTENT_ID, contentId)
                putString(DetailActivity.EXTRA_CONTENT_TYPE, contentType)
            })
        }.also {
            startActivity(it)
        }
    }
}