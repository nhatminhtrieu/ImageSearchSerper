package com.example.imagesearchserper.repository

import com.example.imagesearchserper.api.ApiCallBack
import com.example.imagesearchserper.api.RetrofitInstance.apiService
import com.example.imagesearchserper.model.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageRepository {
    fun fetchImages(queryMap: Map<String, String>, apiCallBack: ApiCallBack<ResponseBody>) {
        val api = apiService
        val call = api.getImages(queryMap)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    apiCallBack.onSuccess(response.body()!!)
                } else {
                    apiCallBack.onError(Throwable("Error fetching images"))
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                apiCallBack.onError(t)
            }
        })
    }
}