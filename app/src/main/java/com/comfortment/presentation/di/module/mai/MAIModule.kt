package com.comfortment.presentation.di.module.mai

import com.comfortment.data.repository.MAIRepositoryImp
import com.comfortment.domain.repository.mai.MAIRepository
import com.comfortment.domain.repository.mai.MyMAIRepository
import com.comfortment.domain.usecases.mai.BringMyMAIUseCase
import com.comfortment.domain.usecases.mai.LoadMyMAIUseCase
import com.comfortment.domain.usecases.mai.MAIUseCase
import com.comfortment.domain.usecases.mai.RegisterMAIUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MAIModule {

    @Provides
    @Singleton
    fun provideMAIRepository(repository: MAIRepositoryImp): MAIRepository = repository

    @Provides
    @Singleton
    fun provideMyMAIRepository(repository: MAIRepositoryImp): MyMAIRepository = repository

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