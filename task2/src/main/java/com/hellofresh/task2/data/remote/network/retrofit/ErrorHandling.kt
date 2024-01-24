package com.hellofresh.task2.data.remote.network.retrofit

import com.hellofresh.task2.data.remote.network.response.ErrorType
import com.hellofresh.task2.data.remote.network.response.ErrorType.NetworkError
import com.hellofresh.task2.data.remote.network.response.ErrorType.SocketTimeout
import com.hellofresh.task2.data.remote.network.response.ErrorType.UnknownError
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.getErrorType(): ErrorType {
    return when (this) {
        is UnknownHostException -> NetworkError
        is SocketTimeoutException -> SocketTimeout
        else -> UnknownError
    }
}