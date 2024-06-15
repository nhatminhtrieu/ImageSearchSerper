//package com.example.imagesearchserper.`object`
//
//import com.example.imagesearchserper.utils.IApi
//import okhttp3.MediaType.Companion.toMediaType
//import okhttp3.OkHttpClient
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//object RetrofitInstance {
//    private const val BASE_URL = "https://google.serper.dev/"
//    private const val API_KEY = "3ee310c54f5a3acb5e5e1c031215c67699d89683"
//    private val contentType = "application/json".toMediaType()
//
//    private val client by lazy {
//
//        OkHttpClient.Builder()
//            .addInterceptor { chain ->
//                val original = chain.request()
//                val requestBuilder = original.newBuilder()
//                    .header("X-API-KEY", API_KEY)
//                    .header("Content-Type", "application/json")
//                val request = requestBuilder.build()
//                chain.proceed(request)
//            }
//            .build()
//    }
//
//    val retrofit: Retrofit by lazy {
//        Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//    }
//
//    private val apiService: IApi by lazy {
//        retrofit.create(IApi::class.java)
//    }
//}