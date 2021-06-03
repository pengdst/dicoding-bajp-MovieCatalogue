package io.github.pengdst.jetpacksubmission.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import dagger.hilt.android.AndroidEntryPoint
import io.github.pengdst.jetpacksubmission.R
import io.github.pengdst.jetpacksubmission.core.HasToolbarFragment
import io.github.pengdst.jetpacksubmission.databinding.FragmentHomeBinding
import io.github.pengdst.jetpacksubmission.domain.models.Section
import io.github.pengdst.jetpacksubmission.ui.home.sections.SectionsPagerAdapter
import io.github.pengdst.jetpacksubmission.ui.home.sections.movie.MovieListFragment
import io.github.pengdst.jetpacksubmission.ui.home.sections.tv.TvShowListFragment
import io.github.pengdst.libs.ui.fragment.viewbinding.FragmentViewBindingDelegate.Companion.viewBindings
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), HasToolbarFragment {

    private val binding: FragmentHomeBinding by viewBindings()
    @Inject
    lateinit var sectionsPagerAdapter: SectionsPagerAdapter

    override val toolbar: Toolbar get() = binding.toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sectionsPagerAdapter.addSection(Section(getString(R.string.movie), MovieListFragment()))
        sectionsPagerAdapter.addSection(Section(getString(R.string.tv_show), TvShowListFragment()))

        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}