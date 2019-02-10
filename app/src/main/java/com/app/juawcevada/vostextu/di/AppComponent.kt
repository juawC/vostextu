package com.app.juawcevada.vostextu.di

import com.app.juawcevada.vostextu.VosTextuApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ApiModule::class,
    ConfigsModule::class,
    ActivityBuildersModule::class
])
interface AppComponent : AndroidInjector<VosTextuApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<VosTextuApplication>()
}