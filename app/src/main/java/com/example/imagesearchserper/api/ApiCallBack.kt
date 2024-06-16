package com.example.imagesearchserper.api

interface ApiCallBack<T> {
    fun onSuccess(data: T)
    fun onError(t: Throwable)
}
