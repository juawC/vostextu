package com.app.juawcevada.vostextu.ui.shared


import androidx.lifecycle.MediatorLiveData

/**
 * MediatorLiveData with helper functions to help update a ViewState
 */
class ViewStateLiveData<T>(initialState: T) : MediatorLiveData<T>() {

    init {
        value = initialState
    }

    val currentState: T
        get() = value!!

    fun updateState(newStateCreator: T.() -> T) {
        value = value!!.newStateCreator()
    }

}