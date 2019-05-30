package com.comfortment.presentation.ui.main.mai

import com.comfortment.domain.model.MAI
import com.comfortment.presentation.ui.base.BaseView
import io.reactivex.Single

interface MAIContract {

    interface View : BaseView<Presenter> {

        fun initCard(list: List<MAI>)

        fun removeCard()

        fun moveEditPage()

    }

    interface Presenter {

        fun loadMyMAI()

        fun loadMAI(buildingNumber: String, floor: String): Single<List<MAI>>

    }
}