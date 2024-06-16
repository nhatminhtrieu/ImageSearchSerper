package com.example.imagesearchserper.api

import com.example.imagesearchserper.model.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IApi {
    @POST("images")
    @Headers("Content-Type: application/json")
    fun getImages(@Body queryMap: Map<String, String>): Call<ResponseBody>
}