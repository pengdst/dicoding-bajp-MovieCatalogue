package io.github.pengdst.jetpacksubmission.ui.favorite.sections.tv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.databinding.FragmentTvShowListBinding
import io.github.pengdst.jetpacksubmission.ui.favorite.FavoriteAdapter
import io.github.pengdst.jetpacksubmission.ui.favorite.FavoriteViewModel
import io.github.pengdst.jetpacksubmission.ui.home.ContentCallback
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class TvShowFavoriteFragment : Fragment() {

    private val binding: FragmentTvShowListBinding by viewBindings()
    private val viewModel: FavoriteViewModel by viewModels()
    @Inject lateinit var favoriteAdapter: FavoriteAdapter
    private var contentCallback: ContentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contentCallback = context as ContentCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        viewModel.getFavoriteData(DataStore.TYPE_TV_SHOW).observe(viewLifecycleOwner){
            showLoading(false)
            favoriteAdapter.submitData(it)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun setupRecyclerView(){
        favoriteAdapter.setOnItemClickListener { _, tv, position ->
            contentCallback?.moveTo(position, tv.id, tv.type)
        }
        binding.rvTvShows.apply {
            adapter = favoriteAdapter
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        }
    }
}