package com.comfortment.data.repository

import com.comfortment.data.model.AuthEntityMapper
import com.comfortment.data.source.auth.AuthLocalDataSource
import com.comfortment.data.source.auth.AuthRemoteDataSource
import com.comfortment.domain.model.Auth
import com.comfortment.domain.repository.AuthRepository
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val mapper: AuthEntityMapper
) : AuthRepository {

    override fun requestAuth(token: String): Single<Auth> =
        authRemoteDataSource.requestAuth(token)
            .map { data ->
                saveAuth(mapper.mapToDomain(data))
                mapper.mapToDomain(data)
            }
            .doOnError { Throwable("Not found!") }

    override fun refreshAuth(refreshToken: String): Single<Auth> =
        authRemoteDataSource.refreshAuth(refreshToken)
            .map { data ->
                saveAuth(mapper.mapToDomain(data))
                mapper.mapToDomain(data)
            }
            .doOnError { Throwable("Not found!") }

    override fun getAuth(): Maybe<Auth> =
        authLocalDataSource.getAuth()
            .map { data -> mapper.mapToDomain(data) }
            .doOnError { Throwable("Not found!") }

    override fun saveAuth(auth: Auth) = authLocalDataSource.saveAuth(mapper.mapToEntity(auth))
}