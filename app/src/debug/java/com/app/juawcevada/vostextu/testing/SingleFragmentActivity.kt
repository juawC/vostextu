package com.app.juawcevada.vostextu.testing

import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.app.juawcevada.vostextu.R
import dagger.android.AndroidInjector
import dagger.android.support.HasSupportFragmentInjector

class SingleFragmentActivity : AppCompatActivity(), HasSupportFragmentInjector {


    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        // Skip dagger inject
        return AndroidInjector {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val content = FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            )
            id = R.id.container
        }
        setContentView(content)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, "TEST")
                .commit()
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit()
    }
}