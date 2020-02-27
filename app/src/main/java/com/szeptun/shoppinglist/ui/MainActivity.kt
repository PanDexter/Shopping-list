package com.szeptun.shoppinglist.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.szeptun.shoppinglist.R
import com.szeptun.shoppinglist.databinding.ActivityMainBinding
import com.szeptun.shoppinglist.ui.Lists.SlideListAdapter

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewPagerAdapter: SlideListAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewPagerAdapter = SlideListAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> getString(R.string.Active)
                else -> getString(R.string.Archive)
            }
        }.attach()
    }
}
