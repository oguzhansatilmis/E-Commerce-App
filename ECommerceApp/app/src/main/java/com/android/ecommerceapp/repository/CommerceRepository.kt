package com.android.ecommerceapp.repository

import com.android.ecommerceapp.base.BaseRepository
import com.android.ecommerceapp.service.ApiService
import javax.inject.Inject

class CommerceRepository @Inject constructor(
    private val apiService: ApiService
) : BaseRepository() {


    suspend fun getCategories() = safeApiCall {

        apiService.getAllCategories()
    }

    suspend fun getAllProduct() = safeApiCall {
        apiService.getAllProducts()
    }

    suspend fun getCategoryItems(category:String)= safeApiCall {
        apiService.getCategoryItems(category)
    }

}