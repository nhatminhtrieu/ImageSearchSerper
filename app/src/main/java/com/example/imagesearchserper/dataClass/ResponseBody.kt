package com.example.imagesearchserper.dataClass

data class ResponseBody<T>(
    val success: Boolean,
    val message: String,
    val data: T
)
