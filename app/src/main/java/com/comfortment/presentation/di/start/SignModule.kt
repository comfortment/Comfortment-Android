package com.comfortment.presentation.di.start

import com.comfortment.data.repository.AuthRepositoryImp
import com.comfortment.domain.usecases.auth.AuthUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SignModule {

    @Singleton
    @Provides
    fun provideAuthUseCase(authRepositoryImp: AuthRepositoryImp): AuthUseCase = AuthUseCase(authRepositoryImp)

}