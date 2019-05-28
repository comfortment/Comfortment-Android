package com.comfortment.presentation.ui.main.mai.maiEdit

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_mai.building_picker
import kotlinx.android.synthetic.main.fragment_mai.floor_picker
import kotlinx.android.synthetic.main.fragment_mai_edit.*

class MAIEditFragment : BaseFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_mai_edit

    private val disturbAdapter = DisturbAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        building_picker.minValue = 1001
        building_picker.maxValue = 1015

        floor_picker.minValue = 1
        floor_picker.maxValue = 15

        disturb_list.layoutManager = LinearLayoutManager(context).apply { reverseLayout = true }
        disturb_list.adapter = disturbAdapter
    }
}