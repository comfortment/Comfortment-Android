package com.comfortment.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.comfortment.data.repository.AuthRepositoryImp
import com.comfortment.data.local.db.AppDatabase
import com.comfortment.data.model.auth.AuthEntityMapper
import com.comfortment.data.repository.MAIRepositoryImp
import com.comfortment.domain.repository.auth.AuthRepository
import com.comfortment.domain.repository.mai.MAIRepository
import com.comfortment.domain.repository.mai.MyMAIRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "comfortment.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun provideAuthEntityMapper(): AuthEntityMapper =
        AuthEntityMapper()

    @Provides
    @Singleton
    fun provideAuthRepository(repository: AuthRepositoryImp): AuthRepository = repository

    @Provides
    @Singleton
    fun provideMAIRepository(repository: MAIRepositoryImp): MAIRepository = repository

    @Provides
    @Singleton
    fun provideMyMAIRepository(repository: MAIRepositoryImp): MyMAIRepository = repository
}