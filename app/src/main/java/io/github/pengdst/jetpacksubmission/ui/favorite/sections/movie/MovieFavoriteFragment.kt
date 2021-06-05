package io.github.pengdst.jetpacksubmission.ui.favorite.sections.movie

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
import io.github.pengdst.jetpacksubmission.databinding.FragmentMovieListBinding
import io.github.pengdst.jetpacksubmission.ui.favorite.FavoriteAdapter
import io.github.pengdst.jetpacksubmission.ui.favorite.FavoriteViewModel
import io.github.pengdst.jetpacksubmission.ui.home.ContentCallback
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class MovieFavoriteFragment : Fragment() {

    private val binding: FragmentMovieListBinding by viewBindings()
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
        viewModel.getFavoriteData(DataStore.TYPE_MOVIE).observe(viewLifecycleOwner) {
            favoriteAdapter.submitData(it)
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun setupRecyclerView(){
        favoriteAdapter.setOnItemClickListener { _, movie, position ->
            contentCallback?.moveTo(position, movie.id, movie.type)
        }
        binding.rvMovies.apply {
            adapter = favoriteAdapter
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
        }
    }
}