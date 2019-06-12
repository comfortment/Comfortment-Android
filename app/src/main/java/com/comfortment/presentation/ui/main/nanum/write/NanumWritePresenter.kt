package com.comfortment.presentation.ui.main.nanum.write

import android.util.Log
import androidx.room.EmptyResultSetException
import com.comfortment.domain.model.MyMAI
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.nanum.GetNanumDetailUseCase
import com.comfortment.domain.usecases.nanum.PostNanumUseCase
import com.comfortment.domain.usecases.nanum.UploadImageUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import com.comfortment.presentation.ui.base.BasePresenter
import javax.inject.Inject

class NanumWritePresenter @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase,
    private val postNanumUseCase: PostNanumUseCase,
    private val getNanumDetailUseCase: GetNanumDetailUseCase,
    bringMyMAIUseCase: BringMyMAIUseCase,
    authUseCase: AuthUseCase,
    appSchedulerProvider: AppSchedulerProvider
) : BasePresenter<NanumWriteContract.View>(authUseCase, appSchedulerProvider), NanumWriteContract.Presenter {

    private var nanumWriteView: NanumWriteContract.View? = null
    private val myMai = bringMyMAIUseCase.createObservable(BringMyMAIUseCase.Params())
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(MyMAI())

    override fun loadNanumData(nanumId: String) {
        nanumWriteView?.showLoading()
        compositeDisposable.add(
            getNanumDetailUseCase.createObservable(
                GetNanumDetailUseCase.Params(
                    accessToken,
                    nanumId
                )
            ).subscribe({
                nanumWriteView?.putNanumData(it)
                nanumWriteView?.hideLoading()
            }, {
                Log.e("asdfd", it.message)
                nanumWriteView?.hideLoading()
            })
        )
    }

    override fun postNanum(
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
        nanumWriteView?.showLoading()
        compositeDisposable.add(
            uploadImageUseCase.createObservable(UploadImageUseCase.Params(accessToken, imageUrl))
                .subscribe({
                    Log.e("qwer", it)
                    compositeDisposable.add(
                        postNanumUseCase.createObservable(
                            PostNanumUseCase.Params(
                                accessToken,
                                myMai.id,
                                type,
                                bankAccount,
                                bank,
                                it,
                                price,
                                expiry,
                                description,
                                "kakao",
                                payAt,
                                title
                            )
                        ).subscribe({ res ->
                            if (res.code() == 201) {
                                nanumWriteView?.goBack()
                                nanumWriteView?.showToast("현재 나눔 업로드 완료!")
                            }
                            nanumWriteView?.hideLoading()
                        }, {
                            nanumWriteView?.showToast("현재 나눔을 올릴 수 없습니다.")
                            nanumWriteView?.hideLoading()
                        })
                    )
                }, {
                    Log.e("qwer", it.message)
                    compositeDisposable.add(
                        postNanumUseCase.createObservable(
                            PostNanumUseCase.Params(
                                accessToken,
                                myMai.id,
                                type,
                                bankAccount,
                                bank,
                                "",
                                price,
                                expiry,
                                description,
                                "kakao",
                                payAt,
                                title
                            )
                        ).subscribe({ res ->
                            if (res.code() == 201) {
                                nanumWriteView?.goBack()
                                nanumWriteView?.showToast("현재 나눔 업로드 완료!")
                            }
                            nanumWriteView?.hideLoading()
                        }, {
                            nanumWriteView?.showToast("현재 나눔을 올릴 수 없습니다.")
                            nanumWriteView?.hideLoading()
                        })
                    )
                    Log.e("asf", it.message)
                })
        )
    }

    override fun takeView(view: NanumWriteContract.View) {
        this.nanumWriteView = view
    }

    override fun dropView() {
        this.nanumWriteView = null
    }

}