package com.comfortment.presentation.ui.main.nanum.show

import android.util.Log
import androidx.room.EmptyResultSetException
import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.nanum.GetNanumDetailUseCase
import com.comfortment.domain.usecases.nanum.JoinNanumUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

class ShowNanumDetailPresenter @Inject constructor(
    private val getNanumDetailUseCase: GetNanumDetailUseCase,
    private val joinNanumUseCase: JoinNanumUseCase,
    bringMyMAIUseCase: BringMyMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) :
    BasePresenter<ShowNanumDetailContract.View>(authUseCase, appSchedulerProvider), ShowNanumDetailContract.Presenter {

    private var showNanumDetailView: ShowNanumDetailContract.View? = null
    private val myMai = bringMyMAIUseCase.createObservable(BringMyMAIUseCase.Params())
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(MyMAI())

    override fun loadNanumDetail(nanumId: String) {
        showNanumDetailView?.showLoading()
        compositeDisposable.add(getNanumDetailUseCase.createObservable(
            GetNanumDetailUseCase.Params(accessToken, nanumId)
        ).subscribe { data, error ->
            showNanumDetailView?.hideLoading()
            if (error != null || data == null) {
                Log.e("sdf", error.message)
                showNanumDetailView?.moveBack()
                showNanumDetailView?.showToast("상세정보를 볼 수 없습니다...")
            } else {
                val payAt = data.payAt == "advanced"
                val bankAccount = "${data.bank} ${data.bankAccount}"

                showNanumDetailView?.initDetail(
                    data.imagePath,
                    data.title!!,
                    data.price,
                    payAt,
                    data.expiry,
                    data.description,
                    data.currentState!!,
                    data.roomNumber!!,
                    data.ownerName!!,
                    data.phoneNumber!!,
                    bankAccount
                )
            }
        })
    }

    override fun joinNanum(nanumId: String, isJoined: Boolean) {
        if (myMai.id.isNotEmpty()) {
            showNanumDetailView?.showLoading()
            compositeDisposable.add(
                joinNanumUseCase.createObservable(JoinNanumUseCase.Params(accessToken, myMai.id, nanumId))
                    .subscribe({
                        if(it.code() == 200){
                            showNanumDetailView?.setJoinBtnText(isJoined)
                        } else {
                            showNanumDetailView?.showToast("현재 오류로 사용 불가 합니다..")
                        }
                        showNanumDetailView?.hideLoading()
                    }, {
                        showNanumDetailView?.hideLoading()
                        showNanumDetailView?.showToast("오류로 사용 불가 합니다..")
                    })
            )
        } else {
            showNanumDetailView?.showToast("자신의 세대 정보를 등록해야\n참여 가능합니다!")
        }
    }

    override fun takeView(view: ShowNanumDetailContract.View) {
        this.showNanumDetailView = view
    }

    override fun dropView() {
        this.showNanumDetailView = null
    }

}