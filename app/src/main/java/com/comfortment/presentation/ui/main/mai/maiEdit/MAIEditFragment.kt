package com.comfortment.presentation.ui.main.mai.maiEdit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.comfortment.R
import com.comfortment.presentation.ui.base.BaseFragment
import com.comfortment.presentation.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.disturb_item.view.*
import kotlinx.android.synthetic.main.fragment_mai.building_picker
import kotlinx.android.synthetic.main.fragment_mai.floor_picker
import kotlinx.android.synthetic.main.fragment_mai_edit.*
import kotlinx.android.synthetic.main.fragment_mai_edit.view.*
import javax.inject.Inject

class MAIEditFragment : BaseFragment(), MAIEditContract.View, View.OnClickListener {

    override val layoutId: Int
        get() = R.layout.fragment_mai_edit

    @Inject
    lateinit var maiEditPresenter: MAIEditPresenter

    private val disturbAdapter = DisturbAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        maiEditPresenter.takeView(this)

        building_picker.minValue = 1
        building_picker.maxValue = 9999

        floor_picker.minValue = 1
        floor_picker.maxValue = 9999

        disturb_list.layoutManager = LinearLayoutManager(context)
        disturb_list.adapter = disturbAdapter

        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.GONE
            fab.hide()
        }

        back_btn.setOnClickListener(this)
        edit_btn.setOnClickListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as MainActivity).apply {
            bottom_app_bar.visibility = View.VISIBLE
            fab.show()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.back_btn -> moveMain()
            R.id.edit_btn -> {
                val buildingNumber = building_picker.value.toString()
                val floor = floor_picker.value.toString()
                val name = name_edit.text.toString()
                val phoneNumber = phone_edit.text.toString()
                val acceptedDecibel = decibel_seek.progress
                val hateNoiseDescription = noise_edit.noise_edit.text.toString()
                val hateSmellDescription = smell_edit.text.toString()
                val etc = etc_edit.text.toString()

                val disturbPairList = ArrayList<Pair<Int, Int>>()

                try {
                    disturbAdapter.viewList.forEach {
                        if (it != null) {
                            val first = it.itemView.first_edit.text.toString().toInt()
                            val second = it.itemView.second_edit.text.toString().toInt()

                            if (first in 0..24 && second in 0..24 && first < second) {
                                disturbPairList.add(Pair(first, second))
                            } else {
                                throw Exception()
                            }
                        }
                    }

                    if (buildingNumber.isNotEmpty() && floor.isNotEmpty() &&
                        name.isNotEmpty() && phoneNumber.isNotEmpty() &&
                        hateNoiseDescription.isNotEmpty() && hateSmellDescription.isNotEmpty()
                    ) {
                        maiEditPresenter.registerMAI(
                            buildingNumber,
                            floor,
                            name,
                            phoneNumber,
                            disturbPairList,
                            acceptedDecibel,
                            hateNoiseDescription,
                            hateSmellDescription,
                            etc,
                            "user"
                        )
                    } else {
                        throw Exception()
                    }
                } catch (e: Exception) {
                    showToast("올바르게 작성해주세요.")
                }
            }
        }
    }

    override fun moveMain() {
        (activity as MainActivity).navController.navigateUp()
    }

    override fun showLoading() = loadingDialog.show(fragmentManager, "Loading")

    override fun hideLoading() = loadingDialog.dismiss()

    override fun showToast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()

}