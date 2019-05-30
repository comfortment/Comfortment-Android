package com.comfortment.presentation.ui.base

import android.util.Log
import androidx.room.EmptyResultSetException
import com.auth0.android.jwt.JWT
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
            userId = it.userId
            accessToken = it.accessToken
            refreshToken = it.refreshToken
            it
        }
        .doOnError { EmptyResultSetException("Not found!") }
        .blockingGet(Auth())

    lateinit var userId: String
    lateinit var accessToken: String
    lateinit var refreshToken: String

    fun isExpirationToken(): Boolean {
        Log.e("asfd", accessToken)
        Log.e("asfd", JWT(accessToken).issuedAt.toString())
        Log.e("asfd", JWT(accessToken).expiresAt.toString())
        return JWT(accessToken).isExpired(0)
    }

    fun refreshAuth(refreshToken: String): Single<Auth> =
        authUseCase.createObservable(AuthUseCase.Params("Bearer $refreshToken", userId, true))
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    abstract fun takeView(view: T)

    abstract fun dropView()
}