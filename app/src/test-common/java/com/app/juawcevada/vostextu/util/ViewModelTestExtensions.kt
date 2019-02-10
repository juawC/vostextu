package com.app.juawcevada.vostextu.util

import androidx.lifecycle.ViewModel
import com.app.juawcevada.vostextu.ui.shared.ViewModelFactory
import dagger.internal.InstanceFactory

/**
 * Creates a one off view model factory for the given view model instance.
 */
fun <T : ViewModel> T.createTestFactory(): ViewModelFactory<T> {
    return ViewModelFactory <T>(InstanceFactory.create(this) as InstanceFactory)
}
