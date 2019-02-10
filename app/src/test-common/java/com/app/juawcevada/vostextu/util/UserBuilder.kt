package com.app.juawcevada.vostextu.util

import com.app.juawcevada.vostextu.model.AddressEntity
import com.app.juawcevada.vostextu.model.CompanyEntity
import com.app.juawcevada.vostextu.model.GeoEntity
import com.app.juawcevada.vostextu.model.UserEntity

@UserDsl
class UserBuilder {
    private var id: Long = 0
    private var address: AddressEntity = AddressEntity("", GeoEntity("", ""), "", "", "")
    private var company: CompanyEntity = CompanyEntity("", "", "")
    private var email: String = ""
    private var name: String = ""
    private var phone: String = ""
    private var username: String = ""
    private var website: String = ""

    fun id (body: () -> Long) {
        id = body()
    }

    fun build() = UserEntity(address, company, email, id, name, phone, username, website)
}

fun user(body: UserBuilder.() -> Unit) = UserBuilder().apply(body).build()