package com.comfortment.presentation.di

import android.app.Application
import com.comfortment.data.di.NetworkModule
import com.comfortment.data.di.RepositoryModule
import com.comfortment.presentation.ComfortmentApplication
import com.comfortment.presentation.di.start.SignModule
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
        ActivityBindingModule::class,
        SignModule::class,
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