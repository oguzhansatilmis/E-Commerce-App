package com.android.ecommerceapp.service

import com.android.ecommerceapp.model.Product
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("products")
    suspend fun getAllProducts():Response<List<Product>>

}