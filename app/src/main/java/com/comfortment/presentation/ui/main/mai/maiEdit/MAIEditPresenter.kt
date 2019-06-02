package com.comfortment.presentation.ui.main.mai.maiEdit

import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.LoadMyMAIUseCase
import com.comfortment.domain.usecases.mai.RegisterMAIUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

class MAIEditPresenter @Inject constructor(
    private val registerMAIUseCase: RegisterMAIUseCase,
    private val myMAIUseCase: LoadMyMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<MAIEditContract.View>(authUseCase, appSchedulerProvider), MAIEditContract.Presenter {

    private var maiEditView: MAIEditContract.View? = null

    override fun loadMyMAI() {
        maiEditView?.showLoading()
        compositeDisposable.add(myMAIUseCase.createObservable(LoadMyMAIUseCase.Params(accessToken, userId))
            .subscribe { mai, _ ->
                if (mai != null) {
                    maiEditView?.putMyMAI(
                        mai.buildingNumber,
                        mai.roomNumber,
                        mai.name,
                        mai.phoneNumber,
                        mai.disturbTimeRange,
                        mai.acceptedDecibel,
                        mai.hateNoiseDescription,
                        mai.hateSmellDescription,
                        mai.etc
                    )
                }
                maiEditView?.hideLoading()
            })
    }

    override fun registerMAI(
        buildingNumber: String,
        roomNumber: String,
        name: String,
        phoneNumber: String,
        disturbTimeRange: List<List<Int>>,
        acceptedDecibel: Int,
        hateNoiseDescription: String,
        hateSmellDescription: String,
        etc: String,
        role: String
    ) {
        maiEditView?.showLoading()
        compositeDisposable.add(
            registerMAIUseCase.createObservable(
                RegisterMAIUseCase.Params(
                    accessToken,
                    userId,
                    buildingNumber.toInt(),
                    roomNumber.toInt(),
                    name,
                    phoneNumber,
                    disturbTimeRange,
                    acceptedDecibel,
                    hateNoiseDescription,
                    hateSmellDescription,
                    etc,
                    role
                )
            ).subscribe({
                if (it.code() == 201) {
                    maiEditView?.moveMain()
                    maiEditView?.showToast("세대정보가 수정되었습니다!")
                } else {
                    maiEditView?.showToast("세대정보를 수정할 수 없습니다...")
                }
                maiEditView?.hideLoading()
            }, {
                maiEditView?.showToast("세대정보를 수정할 수 없습니다...")
                maiEditView?.hideLoading()
            })
        )
    }

    override fun takeView(view: MAIEditContract.View) {
        this.maiEditView = view
    }

    override fun dropView() {
        this.maiEditView = null
    }

}