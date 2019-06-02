package com.comfortment.presentation.ui.main.mai.maiEdit

import android.util.Log
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.RegisterMAIUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

class MAIEditPresenter @Inject constructor(
    private val registerMAIUseCase: RegisterMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<MAIEditContract.View>(authUseCase, appSchedulerProvider), MAIEditContract.Presenter {

    private var maiEditView: MAIEditContract.View? = null

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
                    auth.userId,
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
                Log.e("asdf", it.message)
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