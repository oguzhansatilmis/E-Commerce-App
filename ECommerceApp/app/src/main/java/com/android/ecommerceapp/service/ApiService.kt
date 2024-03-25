package com.android.ecommerceapp.service

import com.android.ecommerceapp.model.Product
import com.android.ecommerceapp.model.ProductItem
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {


    @GET("products/categories")
    suspend fun getAllCategories():Response<List<String>>

    @GET("products")
    suspend fun getAllProducts():Response<List<Product>>

    @GET("products/category/{category}")
    suspend fun getCategoryItems(@Path("category") category: String):Response<List<ProductItem>>

}