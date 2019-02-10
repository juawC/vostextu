package com.app.juawcevada.vostextu.model

data class UserEntity(
    val address: AddressEntity,
    val company: CompanyEntity,
    val email: String,
    val id: Long,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)