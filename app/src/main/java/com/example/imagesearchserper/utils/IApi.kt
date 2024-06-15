package com.example.imagesearchserper.utils

import com.example.imagesearchserper.dataClass.ResponseBody
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IApi {
    @GET("{endpoint}")
    fun getDataWithQuery(@Path("endpoint", encoded = true) endpoint: String, @QueryMap params: Map<String, String>): Call<ResponseBody<JsonElement>>
}