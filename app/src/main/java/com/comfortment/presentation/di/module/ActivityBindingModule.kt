package com.comfortment.presentation.di.module

import com.comfortment.presentation.di.qualifier.PerActivity
import com.comfortment.presentation.ui.main.MainActivity
import com.comfortment.presentation.ui.start.StartActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector
    abstract fun bindingTestActivity(): StartActivity

}