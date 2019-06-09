package com.comfortment.presentation.ui.main.nanum.mine

import com.comfortment.domain.model.Nanum
import com.comfortment.presentation.ui.base.BaseView

interface NanumMineContract {

    interface View : BaseView<Presenter> {
        fun moveEdit(nanumId: String)
        fun moveShowDetail(nanumId: String)
        fun initNanumList(nanumList: List<Nanum>, isStar: Boolean, isRaised: Boolean)
        fun clearNanumList(isRaised: Boolean)
        fun setState(position: Int, currentState: String)
    }

    interface Presenter {
        fun setStateCurrent(position: Int, nanumId: String, oldCurrentState: String, currentState: String)
        fun loadJoinNanum()
        fun loadRaisedNanum()
    }
}