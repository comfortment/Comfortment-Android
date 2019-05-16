package com.comfortment.domain.repository

import com.comfortment.domain.model.Auth
import io.reactivex.Single

interface AuthRepository {
    fun requestAuth(token: String): Single<Auth>
    fun refreshAuth(auth: Auth): Single<Auth>
    fun getAuth(): Single<Auth>
    fun saveAuth(auth: Auth)
}