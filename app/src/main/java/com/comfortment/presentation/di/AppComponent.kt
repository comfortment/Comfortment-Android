package com.comfortment.presentation.di

import android.app.Application
import com.comfortment.presentation.ComfortmentApplication
import com.comfortment.presentation.di.module.*
import com.comfortment.presentation.di.module.auth.AuthModule
import com.comfortment.presentation.di.module.mai.MAIModule
import com.comfortment.presentation.di.module.nanum.NanumModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AuthModule::class,
        MAIModule::class,
        NanumModule::class,
        ActivityBindingModule::class,
        FragmentBindingModule::class,
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