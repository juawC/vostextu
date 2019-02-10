package com.app.juawcevada.vostextu.ui

import com.app.juawcevada.vostextu.ui.postdetail.PostDetailFragment
import com.app.juawcevada.vostextu.ui.postdetail.PostDetailFragmentModule
import com.app.juawcevada.vostextu.ui.posts.PostsFragment
import com.app.juawcevada.vostextu.ui.posts.PostsFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [PostsFragmentModule::class])
    internal abstract fun contributePostsFragment(): PostsFragment

    @ContributesAndroidInjector(modules = [PostDetailFragmentModule::class])
    internal abstract fun contributePostDetailFragment(): PostDetailFragment

}