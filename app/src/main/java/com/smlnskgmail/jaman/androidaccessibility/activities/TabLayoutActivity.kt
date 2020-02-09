package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.adapters.TabLayoutPagerAdapter

class TabLayoutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabLayout: TabLayout = findViewById(R.id.tab_layout_tabs) as TabLayout
        val viewPager: ViewPager = findViewById(R.id.tab_layout_view_pager) as ViewPager
        val res = resources
        val mViewPagerAdapter = TabLayoutPagerAdapter(
            supportFragmentManager, res.getStringArray(R.array.tab_layout_tabs_content)
        )
        viewPager.setAdapter(mViewPagerAdapter)
        tabLayout.setupWithViewPager(viewPager)
        val tabNames = res.getStringArray(R.array.tab_layout_tabs)
        for (i in 0 until tabLayout.getTabCount()) {
            val tab = tabLayout.getTabAt(i)
            if (tab != null && tabNames[i] != null) {
                tab.text = tabNames[i]
            }
        }
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_tab_layout
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(context, TabLayoutActivity::class.java)
        }

    }

}