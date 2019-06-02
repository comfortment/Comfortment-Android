package com.comfortment.presentation.ui.main.mai.maiEdit

import com.comfortment.presentation.ui.base.BaseView

interface MAIEditContract {

    interface View : BaseView<Presenter> {
        fun moveMain()

        fun putMyMAI(
            buildingNumber: Int,
            roomNumber: Int,
            name: String,
            phoneNumber: String,
            disturbTimeRange: List<List<Int>>,
            acceptedDecibel: Int,
            hateNoiseDescription: String,
            hateSmellDescription: String,
            etc: String)
    }

    interface Presenter {

        fun loadMyMAI()

        fun registerMAI(
            buildingNumber: String,
            roomNumber: String,
            name: String,
            phoneNumber: String,
            disturbTimeRange: List<List<Int>>,
            acceptedDecibel: Int,
            hateNoiseDescription: String,
            hateSmellDescription: String,
            etc: String,
            role: String
        )

    }
}