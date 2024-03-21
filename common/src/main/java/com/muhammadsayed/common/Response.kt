package com.muhammadsayed.common

sealed class Response<out R> {
    object Loading : Response<Nothing>()
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is Loading -> "Loading"
        }
    }
}

fun <T> Response<T>.successOr(fallback: T): T {
    return (this as? Response.Success<T>)?.data ?: fallback
}

val <T> Response<T>.data: T?
    get() = (this as? Response.Success)?.data
