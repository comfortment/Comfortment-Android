package com.comfortment.data.source.auth

import com.comfortment.data.model.auth.AuthEntity
import com.comfortment.data.model.auth.AccessTokenEntity
import com.comfortment.data.remote.api.AuthAPI
import com.comfortment.presentation.rx.AppSchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(
    private val api: AuthAPI,
    private val appSchedulerProvider: AppSchedulerProvider
) {

    fun requestAuth(loginToken: String): Single<AuthEntity> =
        api.request(loginToken)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun refreshAuth(refreshToken: String): Single<AccessTokenEntity> =
        api.refresh(refreshToken)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
}