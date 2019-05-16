package com.comfortment.presentation.di.module

import com.comfortment.data.repository.AuthRepositoryImp
import com.comfortment.domain.usecases.auth.AuthUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AuthModule {

    @Singleton
    @Provides
    fun provideAuthUseCase(authRepositoryImp: AuthRepositoryImp): AuthUseCase = AuthUseCase(authRepositoryImp)

}