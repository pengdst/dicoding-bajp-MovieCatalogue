package io.github.pengdst.jetpacksubmission.ui.home.sections.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.base.BaseListAdapter
import io.github.pengdst.jetpacksubmission.data.models.TvShow
import io.github.pengdst.jetpacksubmission.databinding.ItemTvShowBinding

/**
 * Created on 5/11/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
class TvShowListAdapter : BaseListAdapter<TvShow, TvShowListAdapter.ViewHolder>(TvShow.diffCallback) {

    class ViewHolder(private val binding: ItemTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            binding.tvSeriesDate.text = tvShow.releaseDate
            binding.tvSeriesTitle.text = tvShow.title
            binding.tvSeriesDescription.text =
                if (tvShow.storyLine.count() >= 50) "${tvShow.storyLine.slice(0..50)}..." else tvShow.storyLine

            Glide.with(binding.root)
                .load(tvShow.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_baseline_broken_image_24)
                .transform(RoundedCorners(16))
                .override(100, 100)
                .centerCrop()
                .into(binding.ivSeriesThumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClickCallback?.onItemClick(it, getItem(position), position)
        }
    }
}