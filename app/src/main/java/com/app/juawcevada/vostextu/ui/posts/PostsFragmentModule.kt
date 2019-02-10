package com.app.juawcevada.vostextu.ui.posts

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module
abstract class PostsFragmentModule {

    @Binds
    internal abstract fun bindFragment(fragment: PostsFragment): Fragment

}