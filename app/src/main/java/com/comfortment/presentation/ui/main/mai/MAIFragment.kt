package com.comfortment.presentation.ui.main.mai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.comfortment.R
import com.comfortment.presentation.ui.main.mai.maiPager.MAICardPagerAdapter
import com.comfortment.presentation.ui.main.mai.maiPager.ShadowTransformer
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_mai.*
import kotlinx.android.synthetic.main.fragment_mai.view.*

class MAIFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_mai, container, false)

        val maiCardPagerAdapter = MAICardPagerAdapter()
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)
        maiCardPagerAdapter.add(null)

        val shadowTransformer = ShadowTransformer(rootView.mai_pager, maiCardPagerAdapter)
        shadowTransformer.enableScaling(true)

        rootView.mai_pager.adapter = maiCardPagerAdapter
        rootView.mai_pager.offscreenPageLimit = 3
        rootView.mai_pager.setPageTransformer(false, shadowTransformer)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        building_picker.minValue = 1001
        building_picker.maxValue = 1015

        floor_picker.minValue = 1
        floor_picker.maxValue = 15
    }
}