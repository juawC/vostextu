package com.app.juawcevada.vostextu.ui.postdetail

import androidx.fragment.app.Fragment
import com.app.juawcevada.vostextu.di.annotation.PostDetail
import com.app.juawcevada.vostextu.model.PostEntity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class PostDetailFragmentModule {

    @Binds
    internal abstract fun bindFragment(fragment: PostDetailFragment): Fragment

    @Module
    companion object {

        @Provides
        @JvmStatic
        @PostDetail
        internal fun providePost(fragment: PostDetailFragment): PostEntity {
            return PostDetailFragmentArgs.fromBundle(fragment.arguments!!).item
        }
    }
}