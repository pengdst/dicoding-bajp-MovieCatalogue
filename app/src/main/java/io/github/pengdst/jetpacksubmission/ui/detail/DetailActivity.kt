package io.github.pengdst.jetpacksubmission.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.databinding.ActivityDetailBinding
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.longToast
import io.github.pengdst.libs.ui.activity.viewbinding.ActivityViewBindingDelegate.Companion.viewBindings

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTENT_TYPE = "content_type"
        const val EXTRA_CONTENT_POSITION = "content_position"
        const val EXTRA_CONTENT_ID = "content_id"
    }

    private var extras: Bundle? = null
    private val binding: ActivityDetailBinding by viewBindings()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        extras = intent.extras
        viewModel.setSelectedContent(extras?.getString(EXTRA_CONTENT_ID))

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().observe(this){
                showLoading(false)
                when(it){
                    is Resource.Success -> populateDetail(
                        title = it.data.title,
                        genre = it.data.genre,
                        language = it.data.language,
                        releaseDate = it.data.releaseDate,
                        storyLine = it.data.storyLine,
                        posterUrl = it.data.imagePosterUrl,
                        backdropUrl = it.data.imageBackdropUrl
                    )
                    is Resource.Error -> longToast(it.message)
                    is Resource.Loading -> showLoading(true)
                }
            }
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().observe(this){
                showLoading(false)
                when(it){
                    is Resource.Success -> populateDetail(
                        title = it.data.title,
                        genre = it.data.genre,
                        language = it.data.language,
                        releaseDate = it.data.releaseDate,
                        storyLine = it.data.storyLine,
                        posterUrl = it.data.imagePosterUrl,
                        backdropUrl = it.data.imageBackdropUrl
                    )
                    is Resource.Error -> longToast(it.message)
                    is Resource.Loading -> showLoading(true)
                }
            }
            else -> Unit
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun populateDetail(
        title: String?,
        genre: String?,
        language: String?,
        releaseDate: String?,
        storyLine: String?,
        posterUrl: String?,
        backdropUrl: String?
    ) {
        with(binding) {
            toolbar.title = title
            tvTitle.text = title
            tvGenre.text = genre
            tvLanguage.text = language
            tvReleaseDate.text = releaseDate
            tvStoryline.text = storyLine

            Glide.with(applicationContext)
                .load(backdropUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivBackdrop)

            Glide.with(applicationContext)
                .load(posterUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(ivThumbnail)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}