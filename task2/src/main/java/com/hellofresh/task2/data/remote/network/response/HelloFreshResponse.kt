package com.hellofresh.task2.data.remote.network.response

import com.hellofresh.task2.R
import com.hellofresh.task2.data.remote.network.response.ErrorType.UnknownError

sealed class HelloFreshResponse<T>(
    var data: T? = null,
    var loading: Boolean? = false,
    var errorType: ErrorType? = null,
) {
    class Success<T>(data: T) : HelloFreshResponse<T>(data)
    class Loading<T>(loading: Boolean?) : HelloFreshResponse<T>(loading = loading)
    class Error<T>(errorType: ErrorType = UnknownError) :
        HelloFreshResponse<T>(errorType = errorType)
}

enum class ErrorType(val errorMessageId: Int) {
    NetworkError(R.string.network_error),
    SocketTimeout(R.string.timeout_error),
    UnknownError(R.string.unknown_error),
}