package com.app.aasara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {

    private var viewPager2: ViewPager2? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_seek_help)
        initViews()
        setViewPagerAdapter()
    }

    private fun initViews() {
        viewPager2 = findViewById(R.id.homeViewPager)
        tabLayout = findViewById(R.id.homeTabLayout)
    }

    private fun setViewPagerAdapter() {
        val fragmentAdapter = HomeViewPagerAdapter(supportFragmentManager, lifecycle)
        (viewPager2 ?: return).adapter = fragmentAdapter
        TabLayoutMediator(
            tabLayout ?: return, viewPager2 ?: return
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.setIcon(R.drawable.ic_home)
                    tab.text = "Home"
                }
                1 -> {
                    tab.setIcon(R.drawable.ic_store)
                    tab.text = "Store"
                }
                2 -> {
                    tab.setIcon(R.drawable.ic_location)
                    tab.text = "NGOs"
                }
                3 -> {
                    tab.setIcon(R.drawable.ic_donate)
                    tab.text = "Donate"
                }
                4 -> {
                    tab.setIcon(R.drawable.ic_event)
                    tab.text = "Events"
                }
            }
        }.attach()
    }
}
//Ganpati Bappa Morya