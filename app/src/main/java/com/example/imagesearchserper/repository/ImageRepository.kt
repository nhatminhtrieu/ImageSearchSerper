package com.example.imagesearchserper.repository

import com.example.imagesearchserper.api.ApiCallBack
import com.example.imagesearchserper.api.RetrofitInstance.apiService
import com.example.imagesearchserper.model.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//{
//    "searchParameters": {
//    "q": "apple inc",
//    "type": "images",
//    "engine": "google",
//    "num": 10
//},
//    "images": [
//    {
//        "title": "A Strategic Analysis of Apple Inc.",
//        "imageUrl": "https://media.licdn.com/dms/image/C4D12AQFNv_KSo_VCwQ/article-cover_image-shrink_600_2000/0/1638142508773?e=2147483647&v=beta&t=SoxCwfG_3-FF8YnKRQNmBv0k0zOPe26PI6-1Nda-GrE",
//        "imageWidth": 740,
//        "imageHeight": 415,
//        "thumbnailUrl": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTpOBvEgzRNu284eO7Mw-_IKukYnD2CXhGMTs1rjPcJj45uRiyr&s",
//        "thumbnailWidth": 300,
//        "thumbnailHeight": 168,
//        "source": "LinkedIn",
//        "domain": "www.linkedin.com",
//        "link": "https://www.linkedin.com/pulse/strategic-analysis-apple-inc-bidemi-ogedengbe",
//        "googleUrl": "https://www.google.com/imgres?imgurl=https%3A%2F%2Fmedia.licdn.com%2Fdms%2Fimage%2FC4D12AQFNv_KSo_VCwQ%2Farticle-cover_image-shrink_600_2000%2F0%2F1638142508773%3Fe%3D2147483647%26v%3Dbeta%26t%3DSoxCwfG_3-FF8YnKRQNmBv0k0zOPe26PI6-1Nda-GrE&tbnid=E8hnCY8LIxTZ3M&imgrefurl=https%3A%2F%2Fwww.linkedin.com%2Fpulse%2Fstrategic-analysis-apple-inc-bidemi-ogedengbe&docid=gP0JwewjX407kM&w=740&h=415&ved=0ahUKEwjs5rXpwOCGAxVytokEHV1zA9kQvFcIAigA",
//        "position": 1
//    }
//    ]
//}

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