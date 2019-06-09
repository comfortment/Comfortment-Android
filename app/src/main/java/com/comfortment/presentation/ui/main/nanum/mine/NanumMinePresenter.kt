package com.comfortment.presentation.ui.main.nanum.mine

import android.util.Log
import androidx.room.EmptyResultSetException
import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.nanum.GetJoinNanumUseCase
import com.comfortment.domain.usecases.nanum.GetRaisedNanumUseCase
import com.comfortment.domain.usecases.nanum.SetRaisedStateNanumUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

class NanumMinePresenter @Inject constructor(
    private val getJoinNanumUseCase: GetJoinNanumUseCase,
    private val getRaisedNanumUseCase: GetRaisedNanumUseCase,
    private val setRaisedStateNanumUseCase: SetRaisedStateNanumUseCase,
    bringMyMAIUseCase: BringMyMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<NanumMineContract.View>(authUseCase, appSchedulerProvider), NanumMineContract.Presenter {

    private var nanumMineView: NanumMineContract.View? = null

    private val myMai = bringMyMAIUseCase.createObservable(BringMyMAIUseCase.Params())
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(MyMAI())

    override fun loadJoinNanum() {
        nanumMineView?.showLoading()
        nanumMineView?.clearNanumList(false)
        compositeDisposable.add(
            getJoinNanumUseCase.createObservable(GetJoinNanumUseCase.Params(accessToken, myMai.id))
                .subscribe({
                    nanumMineView?.initNanumList(it, isStar = false, isRaised = false)
                    nanumMineView?.hideLoading()
                }, {
                    Log.e("asdf", it.message)
                    nanumMineView?.hideLoading()
                    nanumMineView?.showToast("검색 결과가 없습니다.")
                })
        )
    }

    override fun loadRaisedNanum() {
        nanumMineView?.showLoading()
        nanumMineView?.clearNanumList(true)
        compositeDisposable.add(
            getRaisedNanumUseCase.createObservable(GetRaisedNanumUseCase.Params(accessToken, myMai.id))
                .subscribe({
                    nanumMineView?.initNanumList(it, isStar = false, isRaised = true)
                    nanumMineView?.hideLoading()
                }, {
                    Log.e("asdf", it.message)
                    nanumMineView?.hideLoading()
                    nanumMineView?.showToast("검색 결과가 없습니다.")
                })
        )
    }

    override fun setStateCurrent(position: Int, nanumId: String, oldCurrentState: String, currentState: String) {
        nanumMineView?.showLoading()
        compositeDisposable.add(
            setRaisedStateNanumUseCase.createObservable(
                SetRaisedStateNanumUseCase.Params(
                    accessToken,
                    myMai.id,
                    nanumId,
                    currentState
                )
            ).subscribe({
                if (it.code() == 200) {
                    nanumMineView?.setState(position, currentState)
                } else {
                    nanumMineView?.setState(position, oldCurrentState)
                    nanumMineView?.hideLoading()
                    nanumMineView?.showToast("상태를 변경할 수 없습니다.")
                }
            }, {
                nanumMineView?.setState(position, oldCurrentState)
                nanumMineView?.hideLoading()
                nanumMineView?.showToast("상태를 변경할 수 없습니다.")
            })
        )
    }

    override fun takeView(view: NanumMineContract.View) {
        this.nanumMineView = view
    }

    override fun dropView() {
        this.nanumMineView = null
    }
}