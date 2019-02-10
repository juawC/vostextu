package com.app.juawcevada.vostextu.domain.shared

import arrow.core.Try


abstract class UseCase<in P, R> {

    suspend operator fun invoke(parameters: P): Try<R> = execute(parameters)

    protected abstract suspend fun execute(parameters: P): Try<R>
}

suspend operator fun <Any> UseCase<Unit, Any>.invoke() = this.invoke(Unit)
