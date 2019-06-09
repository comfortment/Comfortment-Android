package com.comfortment.presentation.ui.main.nanum

import android.util.Log
import androidx.room.EmptyResultSetException
import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.nanum.GetNanumUseCase
import com.comfortment.domain.usecases.nanum.GetStarNanumUseCase
import com.comfortment.domain.usecases.nanum.SetStarNanumUseCase
import com.comfortment.presentation.di.qualifier.PerFragment
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

@PerFragment
class NanumPresenter @Inject constructor(
    private val getNanumUseCase: GetNanumUseCase,
    private val getStarNanumUseCase: GetStarNanumUseCase,
    private val setStarNanumUseCase: SetStarNanumUseCase,
    bringMyMAIUseCase: BringMyMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<NanumContract.View>(authUseCase, appSchedulerProvider), NanumContract.Presenter {

    private var nanumView: NanumContract.View? = null
    private val myMai = bringMyMAIUseCase.createObservable(BringMyMAIUseCase.Params())
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(MyMAI())

    override fun loadNanumList(type: String, expiry: String) {
        nanumView?.showLoading()
        nanumView?.clearNanumList()
        compositeDisposable.add(
            getNanumUseCase.createObservable(
                GetNanumUseCase.Params(
                    accessToken,
                    type,
                    expiry
                )
            ).subscribe({
                it.forEach { nanum ->
                    nanumView?.initNanumList(nanum, false)
                }
                nanumView?.hideLoading()
            }, {
                Log.e("asdf", it.message)
                nanumView?.hideLoading()
                nanumView?.showToast("검색 결과가 없습니다.")
            })
        )
    }

    override fun loadStarNanumList() {
        nanumView?.showLoading()
        nanumView?.clearNanumList()
        compositeDisposable.add(
            getStarNanumUseCase.createObservable(GetStarNanumUseCase.Params(accessToken, myMai.id))
                .subscribe({
                    it.forEach { nanum ->
                        nanumView?.initNanumList(nanum, true)
                    }
                    nanumView?.hideLoading()
                }, {
                    nanumView?.hideLoading()
                    nanumView?.showToast("검색 결과가 없습니다.")
                })
        )
    }

    override fun setStar(position: Int, nanumId: String) {
        nanumView?.showLoading()
        compositeDisposable.add(
            setStarNanumUseCase.createObservable(SetStarNanumUseCase.Params(accessToken, myMai.id, nanumId))
                .subscribe({
                    if (it.code() == 200) {
                        nanumView?.setItemStarState(position)
                    } else {
                        nanumView?.showToast("스타를 누를 수 없습니다.")
                    }
                    nanumView?.hideLoading()
                }, {
                    nanumView?.hideLoading()
                    nanumView?.showToast("스타를 누를 수 없습니다.")
                })
        )
    }

    override fun takeView(view: NanumContract.View) {
        this.nanumView = view
    }

    override fun dropView() {
        this.nanumView = null
    }

}