package io.github.pengdst.jetpacksubmission.ui.home.sections

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import io.github.pengdst.jetpacksubmission.data.models.Section

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var sections = mutableListOf<Section>()

    fun submitList(mutableMap: List<Section>) {
        sections = mutableMap.toMutableList()
        notifyDataSetChanged()
    }

    fun addSection(section: Section){
        sections.add(section)
        notifyDataSetChanged()
    }

    override fun getCount() = sections.size

    override fun getItem(position: Int) =
        sections[position].fragment

    override fun getPageTitle(position: Int) = sections[position].title
}