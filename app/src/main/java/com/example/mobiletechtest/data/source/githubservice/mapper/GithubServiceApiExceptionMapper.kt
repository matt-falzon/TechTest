package com.example.mobiletechtest.data.source.githubservice.mapper

import com.example.mobiletechtest.domain.exception.DomainException
import com.example.mobiletechtest.domain.exception.NoConnectivityException
import com.example.mobiletechtest.domain.exception.UnexpectedServerException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun mapToDomainException(throwable: Throwable): DomainException {
    return when (throwable) {
        is DomainException ->
            throwable
        is UnknownHostException, is SocketTimeoutException, is ConnectException ->
            NoConnectivityException()
        else ->
            UnexpectedServerException()
    }
}