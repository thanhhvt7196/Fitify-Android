package com.example.learnandroid.domain.exception

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.resolveError(): Throwable {
    when (this) {
        is SocketTimeoutException -> {
            return NetworkException(errorMessage = "connection error!")
        }
        is ConnectException -> {
            return NetworkException(errorMessage = "No internet access")
        }
        is UnknownHostException -> {
            return NetworkException(errorMessage = "No internet access")
        }
        is HttpException -> {
            when (this.code()) {
                502 -> {
                    return NetworkException(errorCode = this.code(), errorMessage = "internal error")
                }
                401 -> {
                    return AuthenticationException(authMessage = "Authentication error")
                }
                400 -> {
                    return NetworkException.parseException(this)
                }
                404 -> {
                    return  NetworkException(errorCode = this.code(), errorMessage = "Not found URL, please check your endpoint")
                }
                else -> {
                    return this
                }
            }
        }
        else -> {
            return this
        }
    }
}