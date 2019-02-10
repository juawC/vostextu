package com.app.juawcevada.vostextu.shared

import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@Reusable
class AppDispatchers(
    val Main: CoroutineDispatcher,
    val IO: CoroutineDispatcher,
    val Default: CoroutineDispatcher
) {

    @Inject
    constructor() : this(
        Dispatchers.Main,
        Dispatchers.IO,
        Dispatchers.Default
    )
}