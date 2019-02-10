package com.app.juawcevada.vostextu.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostEntity(
    val userId: Long,
    val id: Long,
    val title: String,
    val body: String
) : Parcelable