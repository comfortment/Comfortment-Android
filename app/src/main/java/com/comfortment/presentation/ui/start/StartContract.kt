package com.comfortment.presentation.ui.start

import com.comfortment.presentation.ui.base.BaseView

interface StartContract {

    interface View : BaseView<Presenter> {
        fun moveMain()

        fun showLoading()

        fun hideLoading()
    }

    interface Presenter {
        fun findAuth()

        fun requestAuth(token: String)
    }
}