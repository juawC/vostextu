package com.app.juawcevada.vostextu.di

import com.app.juawcevada.vostextu.ui.MainActivity
import com.app.juawcevada.vostextu.ui.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}