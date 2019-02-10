package com.app.juawcevada.vostextu.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Creates a one off view model factory for the given view model instance.
 */
fun <T : ViewModel> T.createTestFactory(): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(this@createTestFactory.javaClass)) {
                @Suppress("UNCHECKED_CAST")
                return this@createTestFactory as T
            }
            throw IllegalArgumentException("unexpected model class $modelClass")
        }
    }
}
