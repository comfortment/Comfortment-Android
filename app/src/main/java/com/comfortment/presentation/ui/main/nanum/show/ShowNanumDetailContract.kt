package com.comfortment.presentation.ui.main.nanum.show

import com.comfortment.presentation.ui.base.BaseView

interface ShowNanumDetailContract {

    interface View : BaseView<Presenter> {
        fun initDetail()
    }

    interface Presenter {
        fun loadNanum()
    }
}