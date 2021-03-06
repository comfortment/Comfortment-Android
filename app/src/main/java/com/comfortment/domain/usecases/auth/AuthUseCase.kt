package com.comfortment.domain.usecases.auth

import com.comfortment.domain.model.Auth
import com.comfortment.domain.repository.auth.AuthRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Maybe
import io.reactivex.Single

class AuthUseCase(private val authRepository: AuthRepository) :
    UseCase<AuthUseCase.Params, Single<Auth>>() {

    override fun createObservable(params: Params): Single<Auth> =
        if (!params.isRefresh) authRepository.requestAuth(params.token)
        else authRepository.refreshAuth(params.token, params.userId)

    fun createObservable(): Maybe<Auth> = authRepository.getAuth()

    override fun onCleared() {}

    data class Params(val token: String, val userId: String, val isRefresh: Boolean){
        constructor(token : String) : this(token, "", false)
    }
}