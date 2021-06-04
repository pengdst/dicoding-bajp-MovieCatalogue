package io.github.pengdst.jetpacksubmission.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.databinding.FragmentMovieListBinding
import io.github.pengdst.jetpacksubmission.ui.home.ContentCallback
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val binding: FragmentMovieListBinding by viewBindings()
    private val viewModel: FavoriteViewModel by viewModels()
    @Inject
    lateinit var favoriteAdapter: FavoriteAdapter
    private var contentCallback: ContentCallback? = null
    private var type: String? = null

    companion object {
        fun newInstance(type: String): FavoriteFragment {
            val args = bundleOf(
                "type" to type
            )
            val fragment = FavoriteFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            type = it.getString("type")
        }
    }

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
        type?.let {
            viewModel.getFavoriteData(it).observe(viewLifecycleOwner) {
                favoriteAdapter.submitData(it)
                showLoading(false)
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.ltLoading.isVisible = isLoading
    }

    private fun setupRecyclerView() {
        favoriteAdapter.setOnItemClickListener { _, favirute, position ->
            contentCallback?.moveTo(position, favirute.id, favirute.type)
        }
        binding.rvMovies.apply {
            adapter = favoriteAdapter
            layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        }
    }
}