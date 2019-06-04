package com.comfortment.presentation.ui.main.nanum

import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseView

interface NanumContract {

    interface View : BaseView<Presenter> {
        fun moveShowDetail(nanumId: String)
        fun initNanumList(nanum: Nanum)
        fun clearNanumList()
    }

    interface Presenter {
        fun loadNanumList(type: String, expiry: String)
    }

}