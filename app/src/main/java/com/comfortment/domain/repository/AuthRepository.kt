package com.comfortment.domain.repository

import com.comfortment.domain.model.Auth
import io.reactivex.Maybe
import io.reactivex.Single

interface AuthRepository {
    fun requestAuth(token: String): Single<Auth>
    fun refreshAuth(refreshToken: String): Single<Auth>
    fun getAuth(): Maybe<Auth>
    fun saveAuth(auth: Auth)
}