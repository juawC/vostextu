package com.app.juawcevada.vostextu.di

import com.app.juawcevada.vostextu.di.annotation.ApiConfig
import dagger.Module
import dagger.Provides


@Module
class ConfigsModule {

    @Provides
    @ApiConfig
    fun providesApiUrl(): String {
        return "http://jsonplaceholder.typicode.com/"
    }
}