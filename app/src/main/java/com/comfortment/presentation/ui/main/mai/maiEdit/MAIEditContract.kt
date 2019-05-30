package com.comfortment.presentation.ui.main.mai.maiEdit

import com.comfortment.presentation.ui.base.BaseView

interface MAIEditContract {

    interface View : BaseView<Presenter> {
        fun moveMain()
    }

    interface Presenter {

        fun registerMAI(
            buildingNumber: String,
            roomNumber: String,
            name: String,
            phoneNumber: String,
            disturbTimeRange: ArrayList<Pair<Int, Int>>,
            acceptedDecibel: Int,
            hateNoiseDescription: String,
            hateSmellDescription: String,
            etc: String,
            role: String
        )

    }
}