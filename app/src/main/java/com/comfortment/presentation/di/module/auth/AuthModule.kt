package com.comfortment.presentation.di.module.auth

import com.comfortment.data.repository.AuthRepositoryImp
import com.comfortment.domain.repository.auth.AuthRepository
import com.comfortment.domain.usecases.auth.AuthUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(repository: AuthRepositoryImp): AuthRepository = repository

    @Singleton
    @Provides
    fun provideAuthUseCase(authRepositoryImp: AuthRepositoryImp): AuthUseCase = AuthUseCase(authRepositoryImp)

}