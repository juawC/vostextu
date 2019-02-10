package com.app.juawcevada.vostextu.ui.shared

import android.view.View
import androidx.databinding.BindingAdapter


@BindingAdapter("setVisible")
fun setVisible(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}
