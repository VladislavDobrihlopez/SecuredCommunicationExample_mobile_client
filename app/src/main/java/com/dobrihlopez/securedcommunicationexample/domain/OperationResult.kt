package com.dobrihlopez.securedcommunicationexample.domain

sealed interface OperationResult<out V, E> {
    data class Success<out V>(val value: V) : OperationResult<V, Nothing>
    data class Failure<out V, E : Throwable>(val error: E, val data: V? = null) : OperationResult<V, E>
}
