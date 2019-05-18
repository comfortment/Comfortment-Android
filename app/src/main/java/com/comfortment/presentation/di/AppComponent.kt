package com.comfortment.presentation.di

import android.app.Application
import com.comfortment.presentation.di.module.NetworkModule
import com.comfortment.presentation.di.module.RepositoryModule
import com.comfortment.presentation.ComfortmentApplication
import com.comfortment.presentation.di.module.ActivityBindingModule
import com.comfortment.presentation.di.module.AppModule
import com.comfortment.presentation.di.module.AuthModule
import com.comfortment.presentation.di.module.MAIModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        AuthModule::class,
        MAIModule::class,
        ActivityBindingModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<ComfortmentApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}