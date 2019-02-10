package com.app.juawcevada.vostextu.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Gets the value of a LiveData safely.
 */
@Throws(InterruptedException::class)
fun <T> LiveData<T>.observeTest() {
    val observer = Observer<T> { }
    this.observeForever(observer)
}