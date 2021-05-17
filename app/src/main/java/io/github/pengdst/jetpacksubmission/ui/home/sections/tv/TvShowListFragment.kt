package io.github.pengdst.jetpacksubmission.ui.home.sections.tv

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import io.github.pengdst.jetpacksubmission.databinding.FragmentTvShowListBinding
import io.github.pengdst.jetpacksubmission.ui.detail.DetailViewModel
import io.github.pengdst.jetpacksubmission.ui.home.ContentCallback
import io.github.pengdst.jetpacksubmission.ui.home.HomeViewModel
import io.github.pengdst.jetpacksubmission.utils.DataStore
import io.github.pengdst.libs.ui.extensions.viewBindings

class TvShowListFragment : Fragment() {

    private val binding: FragmentTvShowListBinding by viewBindings()
    private lateinit var tvShowListAdapter: TvShowListAdapter
    private lateinit var viewModel: HomeViewModel
    private var contentCallback: ContentCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        tvShowListAdapter = TvShowListAdapter()
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        contentCallback = context as ContentCallback
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        tvShowListAdapter.submitList(viewModel.getTvShowList())
    }

    private fun setupRecyclerView(){
        tvShowListAdapter.setOnItemClickListener { _, tv, position ->
            contentCallback?.moveTo(position, tv.id, DataStore.TYPE_TV_SHOW)
        }
        binding.rvTvShows.adapter = tvShowListAdapter
    }
}