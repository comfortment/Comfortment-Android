package com.comfortment.presentation.ui.main.nanum.show

import com.comfortment.presentation.ui.base.BaseView

interface ShowNanumDetailContract {

    interface View : BaseView<Presenter> {
        fun initDetail(
            title: String,
            price: Int,
            payAt: Boolean,
            expiry: Int,
            description: String?,
            currentState: String,
            roomNumber: Int,
            name: String,
            phoneNumber: String,
            bankAccount: String?
        )

        fun moveBack()
    }

    interface Presenter {
        fun loadNanumDetail(nanumId: String)
        fun joinNanum(nanumId: String)
    }
}