package io.github.pengdst.jetpacksubmission.ui.home.sections.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.data.vo.Resource
import io.github.pengdst.jetpacksubmission.databinding.FragmentMovieListBinding
import io.github.pengdst.jetpacksubmission.ui.home.ContentCallback
import io.github.pengdst.jetpacksubmission.ui.home.HomeViewModel
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.jetpacksubmission.utils.longToast
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private val binding: FragmentMovieListBinding by viewBindings()
    private val viewModel: HomeViewModel by viewModels()
    @Inject lateinit var movieListAdapter: MovieListAdapter
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
        viewModel.getMovies().observe(viewLifecycleOwner){
            showLoading(false)
            when(it){
                is Resource.Success -> movieListAdapter.submitData(lifecycle, it.data)
                is Resource.Error -> context.longToast(it.message)
                is Resource.Loading -> showLoading(true)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun setupRecyclerView(){
        movieListAdapter.setOnItemClickListener { _, movie, position ->
            contentCallback?.moveTo(position, movie.id, DataStore.TYPE_MOVIE)
        }
        binding.rvMovies.adapter = movieListAdapter
    }
}