package com.comfortment.presentation.ui.main.mai

import com.comfortment.domain.model.MAI
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.LoadMyMAIUseCase
import com.comfortment.domain.usecases.mai.MAIUseCase
import com.comfortment.presentation.di.qualifier.PerFragment
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import io.reactivex.Single
import javax.inject.Inject

@PerFragment
class MAIPresenter @Inject constructor(
    private val loadMyMAIUseCase: LoadMyMAIUseCase,
    private val maiUseCase: MAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) :
    MAIContract.Presenter, BasePresenter<MAIContract.View>(authUseCase, appSchedulerProvider) {

    private var maiView: MAIContract.View? = null

    override fun loadMyMAI() {
        maiView?.showLoading()
        compositeDisposable.add(loadMyMAIUseCase.createObservable(LoadMyMAIUseCase.Params(accessToken, userId))
            .subscribe { mai, error ->
                if (mai != null) {
                    val buildingNumber = mai.buildingNumber.toString()
                    var floor = mai.roomNumber.toString()

                    floor =
                        if (floor.length > 4) floor.substring(0, 2)
                        else floor.substring(0, 1)

                    loadMAI(buildingNumber, floor).subscribe { list, er ->
                        if (list != null) maiView?.initCard(list)
                        if (er != null) maiView?.showToast("세대 정보가 없습니다!")
                    }
                }
                if (error != null) {
                    maiView?.showToast("자신의 세대 정보를 입력해주세요!")
                }
                maiView?.hideLoading()
            })
    }

    override fun loadMAI(buildingNumber: String, floor: String): Single<List<MAI>> =
        maiUseCase.createObservable(MAIUseCase.Params(accessToken, buildingNumber.toInt(), floor.toInt()))

    fun loadMAI(buildingNumber: Int, floor: Int) {
        maiView?.showLoading()
        compositeDisposable.add(
            maiUseCase.createObservable(
                MAIUseCase.Params(
                    accessToken,
                    buildingNumber,
                    floor
                )
            ).subscribe { list, er ->
                if (list != null) maiView?.initCard(list)
                if (er != null) maiView?.showToast("세대 정보가 없습니다!")

                maiView?.hideLoading()
            })
    }


    override fun takeView(view: MAIContract.View) {
        this.maiView = view
    }

    override fun dropView() {
        this.maiView = null
    }
}