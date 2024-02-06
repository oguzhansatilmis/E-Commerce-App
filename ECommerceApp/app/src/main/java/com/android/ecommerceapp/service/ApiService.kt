package com.android.ecommerceapp.service

import com.android.ecommerceapp.model.Product
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET("products/categories")
    suspend fun getAllCategories():Response<List<String>>

    @GET("products")
    suspend fun getAllProducts():Response<List<Product>>

}