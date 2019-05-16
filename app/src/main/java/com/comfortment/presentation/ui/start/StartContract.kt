package com.comfortment.presentation.ui.start

import com.comfortment.presentation.ui.base.BasePresenter
import com.comfortment.presentation.ui.base.BaseView

interface StartContract {

    interface View : BaseView<Presenter> {
        fun showSignButton()

        fun moveMain()
    }

    interface Presenter : BasePresenter<View> {

        fun findAuth()

        fun requestAuth(token: String)

    }
}