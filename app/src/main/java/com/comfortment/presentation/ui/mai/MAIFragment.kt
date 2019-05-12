package com.comfortment.presentation.ui.mai

import android.os.Bundle
import android.view.View
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.mai.maiPager.MAICardPagerAdapter
import com.comfortment.presentation.ui.mai.maiPager.ShadowTransformer
import kotlinx.android.synthetic.main.fragment_mai.*

class MAIFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_mai

    private val maiCardPagerAdapter by lazy { MAICardPagerAdapter() }
    private val shadowTransformer by lazy {
        ShadowTransformer(mai_pager, maiCardPagerAdapter).apply {
            enableScaling(true)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)

        mai_pager.adapter = maiCardPagerAdapter
        mai_pager.setPageTransformer(false, shadowTransformer)
        mai_pager.offscreenPageLimit = 3

    }
}