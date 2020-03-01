package com.szeptun.shoppinglist.ui.Lists

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.szeptun.shoppinglist.entity.ListState

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = TABS_AMOUNT

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> ListsFragment.newInstance(ListState.ACTIVE)
            else -> ListsFragment.newInstance(ListState.ARCHIVE)
        }

    companion object {
        const val TABS_AMOUNT = 2
    }
}