package com.comfortment.data.source.auth

import com.comfortment.data.model.AuthEntity
import com.comfortment.data.remote.api.AuthAPI
import io.reactivex.Single
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val api: AuthAPI) {

    fun requestAuth(loginToken: String): Single<AuthEntity> = api.request(loginToken)

    fun refreshAuth(refreshToken: String): Single<AuthEntity> = api.refresh(refreshToken)

}