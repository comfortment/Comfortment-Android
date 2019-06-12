package com.comfortment.presentation.ui.main.nanum.write

import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseView

interface NanumWriteContract {

    interface View : BaseView<Presenter> {
        fun putNanumData(nanum: Nanum)
        fun goBack()
    }

    interface Presenter {
        fun postNanum(
            title: String,
            price: String,
            payAt: String,
            type: String,
            expiry: Int,
            description: String,
            bank: String,
            bankAccount: String,
            currentState: String,
            imageUrl: String
        )
        fun loadNanumData(nanumId: String)
    }

}