package com.example.imagesearchserper.model

import java.io.Serializable

data class SearchParameter(
    val q: String,
    val type: String,
    val page: Int,
    val engine: String,
    val num: Int
) : Serializable