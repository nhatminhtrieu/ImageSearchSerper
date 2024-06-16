package com.example.imagesearchserper.model

data class ResponseBody(
    val searchParameters: SearchParameter,
    val images: List<Image>
)
