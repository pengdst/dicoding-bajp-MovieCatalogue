package io.github.pengdst.jetpacksubmission.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.pengdst.jetpacksubmission.databinding.FragmentMovieListBinding
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.libs.ui.extensions.viewBindings

class MovieListFragment : Fragment() {

    private val binding: FragmentMovieListBinding by viewBindings()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        movieAdapter = MovieAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter.submitList(DataStore.movies)
        binding.rvMovies.apply {
            adapter = movieAdapter
        }
    }
}