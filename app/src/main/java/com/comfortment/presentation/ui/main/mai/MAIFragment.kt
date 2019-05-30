package com.comfortment.presentation.ui.main.mai

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.comfortment.R
import com.comfortment.domain.model.MAI
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import com.comfortment.presentation.ui.main.mai.maiPager.MAICardPagerAdapter
import com.comfortment.presentation.ui.main.mai.maiPager.ShadowTransformer
import kotlinx.android.synthetic.main.fragment_mai.*
import javax.inject.Inject

class MAIFragment : BaseFragment(), MAIContract.View, View.OnClickListener {

    private lateinit var shadowTransformer: ShadowTransformer
    private val maiCardPagerAdapter = MAICardPagerAdapter()

    override val layoutId: Int
        get() = R.layout.fragment_mai

    @Inject
    lateinit var maiPresenter: MAIPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        maiPresenter.takeView(this)
        maiPresenter.loadMyMAI()

        building_picker.minValue = 1
        building_picker.maxValue = 9999

        floor_picker.minValue = 1
        floor_picker.maxValue = 99

        find_btn.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        maiPresenter.loadMAI(building_picker.value, floor_picker.value)
    }

    override fun moveEditPage() {
        (activity as MainActivity).apply {
            navController.navigate(R.id.MAIEditFragment)
        }
    }

    override fun initCard(list: List<MAI>) {
        removeCard()

        list.forEach { maiCardPagerAdapter.add(it) }
        mai_pager.adapter = maiCardPagerAdapter

        shadowTransformer = ShadowTransformer(mai_pager, maiCardPagerAdapter)
        shadowTransformer.enableScaling(true)
        mai_pager.setPageTransformer(false, shadowTransformer)
    }

    override fun removeCard() {
        maiCardPagerAdapter.removeAll()
    }

    override fun showLoading() = loadingDialog.show(fragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

}