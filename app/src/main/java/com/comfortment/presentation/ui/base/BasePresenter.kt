package com.comfortment.presentation.ui.base

import androidx.room.EmptyResultSetException
import com.comfortment.domain.model.Auth
import com.comfortment.domain.usecases.auth.AuthUseCase
import com.comfortment.presentation.rx.AppSchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T>(
    val authUseCase: AuthUseCase,
    val appSchedulerProvider: AppSchedulerProvider
) {

    val compositeDisposable = CompositeDisposable()
    val auth: Auth = authUseCase.createObservable()
        .map {
            accessToken = it.accessToken
            it
        }
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(Auth())

    lateinit var accessToken: String

    fun refreshAuth(refreshToken: String): Single<Auth> =
        authUseCase.createObservable(AuthUseCase.Params(refreshToken, true))

    abstract fun takeView(view: T)

    abstract fun dropView()
}