package com.comfortment.domain.repository.auth

import com.comfortment.domain.model.Auth
import io.reactivex.Maybe
import io.reactivex.Single

interface AuthRepository {
    fun requestAuth(token: String): Single<Auth>
    fun refreshAuth(refreshToken: String, userId: String): Single<Auth>
    fun getAuth(): Maybe<Auth>
    fun saveAuth(auth: Auth)
}