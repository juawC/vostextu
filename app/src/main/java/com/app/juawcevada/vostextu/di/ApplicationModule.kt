package com.app.juawcevada.vostextu.di

import android.content.Context
import com.app.juawcevada.vostextu.VosTextuApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    internal abstract fun provideContext(application: VosTextuApplication): Context
}