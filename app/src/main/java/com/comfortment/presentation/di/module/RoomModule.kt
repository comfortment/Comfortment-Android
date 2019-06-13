package com.comfortment.presentation.di.module

import android.content.Context
import androidx.room.Room
import com.comfortment.data.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDataBase(context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "comfortment.db"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

}