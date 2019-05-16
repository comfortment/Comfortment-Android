package com.comfortment.domain.usecases.auth

import com.comfortment.domain.model.Auth
import com.comfortment.domain.repository.AuthRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class AuthUseCase(private val authRepository: AuthRepository) :
    UseCase<AuthUseCase.Params, Single<Auth>>() {

    override fun createObservable(params: Params?): Single<Auth> = authRepository.requestAuth(params!!.token)

    fun createObservable(): Single<Auth> = authRepository.getAuth()

    override fun onCleared() {}

    data class Params(val token: String)
}