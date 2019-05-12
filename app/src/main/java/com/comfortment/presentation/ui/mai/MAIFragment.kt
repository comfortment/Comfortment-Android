package com.comfortment.presentation.ui.mai

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.comfortment.R
import com.comfortment.presentation.ui.mai.maiPager.MAICardPagerAdapter
import com.comfortment.presentation.ui.mai.maiPager.ShadowTransformer
import kotlinx.android.synthetic.main.fragment_mai.view.*

class MAIFragment : Fragment() {

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
}