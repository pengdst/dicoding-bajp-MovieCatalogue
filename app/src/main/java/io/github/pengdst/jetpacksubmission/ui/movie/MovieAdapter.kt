package io.github.pengdst.jetpacksubmission.ui.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.data.models.Movie
import io.github.pengdst.jetpacksubmission.databinding.ItemMovieBinding

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class MovieAdapter : ListAdapter<Movie, MovieAdapter.ViewHolder>(Movie.diffCallback) {
    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieDate.text = movie.releaseDate
            binding.tvMovieTitle.text = movie.title
            binding.tvMovieDescription.text =
                if (movie.storyLine.count() >= 50) "${movie.storyLine.slice(0..50)}..." else movie.storyLine

            Glide.with(binding.root)
                .load(movie.imageUrl)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivMovieThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}