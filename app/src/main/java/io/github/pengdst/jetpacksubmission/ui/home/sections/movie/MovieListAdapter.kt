package io.github.pengdst.jetpacksubmission.ui.home.sections.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.base.BaseListAdapter
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Movie
import io.github.pengdst.jetpacksubmission.databinding.ItemMovieBinding
import javax.inject.Inject

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieListAdapter @Inject constructor()  : BaseListAdapter<Movie, MovieListAdapter.ViewHolder>(Movie.diffCallback) {

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieDate.text = movie.releaseDate
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieDescription.text =
                if (movie.storyLine.count() >= 50) "${movie.storyLine.slice(0..50)}..." else movie.storyLine

            Glide.with(binding.root)
                .load(movie.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .transform(RoundedCorners(16))
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .override(100, 100)
                .centerCrop()
                .into(binding.ivMovieThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClickCallback?.onItemClick(it, getItem(position), position)
        }
    }
}