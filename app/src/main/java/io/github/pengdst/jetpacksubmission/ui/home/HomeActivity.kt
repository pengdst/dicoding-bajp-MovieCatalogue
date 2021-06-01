package io.github.pengdst.jetpacksubmission.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.domain.models.Section
import io.github.pengdst.jetpacksubmission.databinding.ActivityHomeBinding
import io.github.pengdst.jetpacksubmission.ui.detail.DetailActivity
import io.github.pengdst.jetpacksubmission.ui.home.sections.SectionsPagerAdapter
import io.github.pengdst.jetpacksubmission.ui.home.sections.movie.MovieListFragment
import io.github.pengdst.jetpacksubmission.ui.home.sections.tv.TvShowListFragment
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ContentCallback {

    private val binding: ActivityHomeBinding by viewBindings()
    @Inject lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sectionsPagerAdapter.addSection(Section(getString(R.string.movie), MovieListFragment()))
        sectionsPagerAdapter.addSection(Section(getString(R.string.tv_show), TvShowListFragment()))

        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
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