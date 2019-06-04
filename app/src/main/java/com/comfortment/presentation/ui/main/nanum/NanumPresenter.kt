package com.comfortment.presentation.ui.main.nanum

import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.nanum.GetNanumUseCase
import com.comfortment.presentation.di.qualifier.PerFragment
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

@PerFragment
class NanumPresenter @Inject constructor(
    private val getNanumUseCase: GetNanumUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<NanumContract.View>(authUseCase, appSchedulerProvider), NanumContract.Presenter {

    private var nanumView: NanumContract.View? = null

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
                    nanumView?.initNanumList(nanum)
                }
                nanumView?.hideLoading()
            }, {
                nanumView?.hideLoading()
                nanumView?.showToast("검색 결과가 없습니다.")
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