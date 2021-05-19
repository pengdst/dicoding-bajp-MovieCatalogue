package io.github.pengdst.jetpacksubmission.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.databinding.ActivityDetailBinding
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.libs.ui.extensions.viewBindings

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_CONTENT_TYPE = "content_type"
        const val EXTRA_CONTENT_POSITION = "content_position"
    }

    private var extras: Bundle? = null
    private val binding: ActivityDetailBinding by viewBindings()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        extras = intent.extras
        viewModel.setSelectedContent(extras?.getInt(EXTRA_CONTENT_POSITION))

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        val title = when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().title
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().title
            else -> null
        }
        val genre = when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().genre
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().genre
            else -> null
        }
        val language = when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().language
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().language
            else -> null
        }
        val releaseDate = when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().releaseDate
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().releaseDate
            else -> null
        }
        val storyLine = when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().storyLine
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().storyLine
            else -> null
        }
        val imageUrl = when (extras?.getString(EXTRA_CONTENT_TYPE)) {
            DataStore.TYPE_MOVIE -> viewModel.getMovie().imageUrl
            DataStore.TYPE_TV_SHOW -> viewModel.getTvShow().imageUrl
            else -> null
        }

        with(binding) {
            toolbar.title = title
            tvTitle.text = title
            tvGenre.text = genre
            tvLanguage.text = language
            tvReleaseDate.text = releaseDate
            tvStoryline.text = storyLine

            Glide.with(applicationContext)
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivBackdrop)

            Glide.with(applicationContext)
                .load(imageUrl)
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