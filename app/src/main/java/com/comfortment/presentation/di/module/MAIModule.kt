package com.comfortment.presentation.di.module

import com.comfortment.data.repository.MAIRepositoryImp
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.mai.LoadMyMAIUseCase
import com.comfortment.domain.usecases.mai.MAIUseCase
import com.comfortment.domain.usecases.mai.RegisterMAIUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MAIModule {

    @Singleton
    @Provides
    fun provideMAIUseCase(maiRepositoryImp: MAIRepositoryImp): MAIUseCase = MAIUseCase(maiRepositoryImp)

    @Singleton
    @Provides
    fun provideLoadMyMAIUseCase(maiRepositoryImp: MAIRepositoryImp): LoadMyMAIUseCase = LoadMyMAIUseCase(maiRepositoryImp)

    @Singleton
    @Provides
    fun provideBringMyMAIUseCase(maiRepositoryImp: MAIRepositoryImp): BringMyMAIUseCase = BringMyMAIUseCase(maiRepositoryImp)

    @Singleton
    @Provides
    fun provideRegisterMAIUseCase(maiRepositoryImp: MAIRepositoryImp): RegisterMAIUseCase = RegisterMAIUseCase(maiRepositoryImp)

}