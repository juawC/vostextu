package com.app.juawcevada.vostextu.model

data class AddressEntity(
    val city: String,
    val geo: GeoEntity,
    val street: String,
    val suite: String,
    val zipcode: String
)