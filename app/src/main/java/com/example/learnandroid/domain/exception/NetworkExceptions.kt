package com.example.learnandroid.domain.exception

import org.json.JSONObject
import retrofit2.HttpException

open class NetworkException(val errorCode: Int = -1, val errorMessage: String, val response: String = "") :
    Exception() {
    override val message: String?
        get() = localizedMessage

    override fun getLocalizedMessage(): String? {
        return  errorMessage
    }

    companion object {
        fun parseException(e: HttpException): NetworkException {
            var errorBody = e.response()?.errorBody()?.string()

            return try {
                NetworkException(e.code(), JSONObject(errorBody!!).getString("message"))
            } catch (_: Exception) {
                NetworkException(e.code(), "unexpected error!!")
            }
        }
    }
}

class AuthenticationException(authMessage: String) : NetworkException(errorMessage = authMessage) {

}