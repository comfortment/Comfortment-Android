package com.comfortment.presentation.ui.main.nanum.edit

import android.util.Log
import androidx.room.EmptyResultSetException
import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.nanum.EditNanumUseCase
import com.comfortment.domain.usecases.nanum.GetNanumDetailUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

class NanumEditPresenter @Inject constructor(
    private val editNanumUseCase: EditNanumUseCase,
    private val getNanumDetailUseCase: GetNanumDetailUseCase,
    bringMyMAIUseCase: BringMyMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<NanumEditContract.View>(authUseCase, appSchedulerProvider), NanumEditContract.Presenter {

    private var nanumEditView: NanumEditContract.View? = null
    private val myMai = bringMyMAIUseCase.createObservable(BringMyMAIUseCase.Params())
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(MyMAI())

    override fun loadNanumData(nanumId: String) {
        nanumEditView?.showLoading()
        compositeDisposable.add(
            getNanumDetailUseCase.createObservable(
                GetNanumDetailUseCase.Params(
                    accessToken,
                    nanumId
                )
            ).subscribe({
                nanumEditView?.putNanumData(it)
                nanumEditView?.hideLoading()
            }, {
                Log.e("asdfd", it.message)
                nanumEditView?.hideLoading()
            })
        )
    }

    override fun editNanum(
        nanumId: String,
        title: String,
        price: String,
        payAt: String,
        type: String,
        expiry: Int,
        description: String,
        bank: String,
        bankAccount: String,
        currentState: String,
        imageUrl: String
    ) {
        nanumEditView?.showLoading()
        compositeDisposable.add(
            editNanumUseCase.createObservable(
                EditNanumUseCase.Params(
                    accessToken,
                    myMai.id,
                    nanumId,
                    type,
                    bankAccount,
                    bank,
                    imageUrl,
                    price,
                    expiry,
                    description,
                    "kakao",
                    payAt,
                    title,
                    currentState
                )
            ).subscribe({
                if (it.code() == 200) {
                    nanumEditView?.hideLoading()
                    nanumEditView?.showToast("나눔 수정 완료!")
                    nanumEditView?.goBack()
                } else {
                    nanumEditView?.showToast("현재 나눔을 수정 할 수 없습니다.")
                }
            }, {
                nanumEditView?.showToast("현재 나눔을 수정 할 수 없습니다.")
                nanumEditView?.hideLoading()
            })
        )
    }

    override fun takeView(view: NanumEditContract.View) {
        this.nanumEditView = view
    }

    override fun dropView() {
        this.nanumEditView = null
    }

}