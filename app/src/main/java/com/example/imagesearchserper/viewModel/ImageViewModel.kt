package com.example.imagesearchserper.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagesearchserper.api.ApiCallBack

import com.example.imagesearchserper.model.Image
import com.example.imagesearchserper.model.ResponseBody
import com.example.imagesearchserper.repository.ImageRepository

class ImageViewModel : ViewModel() {
    val imagesLiveData = MutableLiveData<List<Image>>()
    private val imageRepository = ImageRepository()

    fun getImages(query: String, page: Int) {
        val queryMap = mapOf(
            "q" to query,
            "type" to "images",
            "page" to page.toString(),
            "engine" to "google",
            "num" to "10"
        )

        imageRepository.fetchImages(queryMap, object : ApiCallBack<ResponseBody> {
            override fun onSuccess(data: ResponseBody) {
                imagesLiveData.postValue(data.images)
                Log.d("ImageViewModel", "Images fetched: $data")
            }

            override fun onError(t: Throwable) {
                Log.e("ImageViewModel", "Error: ${t.message}")
            }
        })
    }
}