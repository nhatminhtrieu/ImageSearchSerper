package com.example.imagesearchserper.utils

interface ApiCallBack<T> {
    fun onSuccess(data: T)
    fun onError(error: Throwable)
}