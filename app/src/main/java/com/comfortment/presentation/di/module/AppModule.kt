package com.comfortment.presentation.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, RoomModule::class])
class AppModule {

    @Singleton
    @Provides
    fun bindContext(application: Application): Context = application

}