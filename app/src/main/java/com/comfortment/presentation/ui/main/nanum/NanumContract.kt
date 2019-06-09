package com.comfortment.presentation.ui.main.nanum

import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseView

interface NanumContract {

    interface View : BaseView<Presenter> {
        fun moveShowDetail(nanumId: String)
        fun initNanumList(nanum: Nanum, isStar: Boolean)
        fun clearNanumList()
        fun setItemStarState(position: Int)
    }

    interface Presenter {
        fun loadNanumList(type: String, expiry: String)
        fun loadStarNanumList()
        fun setStar(position: Int, nanumId: String)
    }
}