package com.szeptun.shoppinglist.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.szeptun.shoppinglist.R
import com.szeptun.shoppinglist.databinding.ActivityMainBinding
import com.szeptun.shoppinglist.ui.Lists.ViewPagerAdapter
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewPagerAdapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.Active)
                else -> getString(R.string.Archive)
            }
        }.attach()
    }
}
