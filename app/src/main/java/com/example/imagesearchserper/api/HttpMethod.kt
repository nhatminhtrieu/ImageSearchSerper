package com.example.imagesearchserper.api

import com.example.imagesearchserper.dataClass.ResponseBody
//import com.example.imagesearchserper.`object`.RetrofitInstance
import com.example.imagesearchserper.utils.ApiCallBack
import com.example.imagesearchserper.utils.IApi
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

//class HttpMethod {
//    var retrofitBuilder: Retrofit = RetrofitInstance.retrofit
//
//    inline fun <reified T : Any> doGetWithQuery(endpoint: String, params: Map<String, String>, callback: ApiCallBack<Any>) {
//        val api = retrofitBuilder.create(IApi::class.java)
//        val call = api.getDataWithQuery(endpoint, params)
//
//        call.enqueue(object : Callback<ResponseBody<JsonElement>> {
//            override fun onResponse(
//                call: Call<ResponseBody<JsonElement>>,
//                response: Response<ResponseBody<JsonElement>>
//            ) {
//                if (response.isSuccessful) {
//                    val res = response.body()
//                    val gson = Gson()
//                    val data: Any = if (res?.data?.isJsonArray == true) {
//                        gson.fromJson<List<T>>(res.data, object : TypeToken<List<T>>() {}.type) ?: emptyList<T>()
//                    } else {
//                        gson.fromJson<T>(res?.data, object : TypeToken<T>() {}.type) ?: Any()
//                    }
//                    callback.onSuccess(data)
//                } else {
//                    callback.onError(Throwable(response.message()))
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseBody<JsonElement>>, t: Throwable) {
//                callback.onError(t)
//            }
//        })
//    }
//
//}