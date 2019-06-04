package com.comfortment.presentation.di.module

import com.comfortment.presentation.di.qualifier.PerFragment
import com.comfortment.presentation.ui.main.nanum.NanumFragment
import com.comfortment.presentation.ui.main.mai.MAIFragment
import com.comfortment.presentation.ui.main.mai.maiEdit.MAIEditFragment
import com.comfortment.presentation.ui.main.mai.maiShow.MAIShowFragment
import com.comfortment.presentation.ui.main.nanum.show.ShowNanumDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMAIFragment(): MAIFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMAIShowFragment(): MAIShowFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingMAIEditFragment(): MAIEditFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingNanumFragment(): NanumFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindingShowNanumDetailFragment(): ShowNanumDetailFragment

}