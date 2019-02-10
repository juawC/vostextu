package com.app.juawcevada.vostextu.di

import com.app.juawcevada.vostextu.data.ApiService
import com.app.juawcevada.vostextu.di.annotation.ApiConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import dagger.Reusable


/**
 * App module
 */
@Module
class ApiModule {

    @Provides
    @Reusable
    internal fun provideApi(
        @ApiConfig url: String,
        okHttpClient: OkHttpClient): ApiService {

        val retrofit = Retrofit.Builder()
                .callFactory(okHttpClient)
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

        return OkHttpClient.Builder().addInterceptor(logging).build()
    }
}