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
            navController.navigate(R.id.MAIEditFragment,  null, navOptions)
        }
    }

    override fun initCard(list: List<MAI>) {
        if (mai_pager != null) {
            removeCard()

            list.forEach { maiCardPagerAdapter.add(it) }
            mai_pager.adapter = maiCardPagerAdapter

            shadowTransformer = ShadowTransformer(mai_pager, maiCardPagerAdapter)
            shadowTransformer.enableScaling(true)
            mai_pager.setPageTransformer(false, shadowTransformer)

            hideNotFoundText()
        }
    }

    override fun removeCard() {
        maiCardPagerAdapter.removeAll()
    }

    override fun showNotFoundText(isFoundMine: Boolean) {
        if (isFoundMine) not_found_tv?.text = "세대 정보가 없습니다."
        else not_found_tv?.text = "자신의 세대 정보를 입력해 주세요!"
        not_found_tv?.visibility = View.VISIBLE
    }

    override fun hideNotFoundText() {
        not_found_tv?.visibility = View.INVISIBLE
    }

    override fun showLoading() {
        if(!(activity as MainActivity).loadingDialog.isAdded)
            (activity as MainActivity).loadingDialog.show(fragmentManager!!, "Loading")
    }

    override fun hideLoading() = (activity as MainActivity).loadingDialog.dismiss()

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

}