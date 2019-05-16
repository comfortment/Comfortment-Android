package com.comfortment.data.di

import android.content.Context
import androidx.room.Room
import com.comfortment.data.repository.AuthRepositoryImp
import com.comfortment.data.local.db.AppDatabase
import com.comfortment.data.model.AuthEntityMapper
import com.comfortment.domain.repository.AuthRepository
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
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideAuthEntityMapper(): AuthEntityMapper = AuthEntityMapper()

    @Provides
    @Singleton
    fun provideAuthRepository(repository: AuthRepositoryImp): AuthRepository = repository

}