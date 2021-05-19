package io.github.pengdst.jetpacksubmission.ui.home.sections

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import io.github.pengdst.jetpacksubmission.data.source.domain.models.Section
import javax.inject.Inject

class SectionsPagerAdapter @Inject constructor (
    fa: FragmentActivity
) : FragmentPagerAdapter(fa.supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var sections = mutableListOf<Section>()

    fun addSection(section: Section){
        sections.add(section)
        notifyDataSetChanged()
    }

    override fun getCount() = sections.size

    override fun getItem(position: Int) =
        sections[position].fragment

    override fun getPageTitle(position: Int) = sections[position].title
}